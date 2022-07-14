/**
 * This behaves like a WebSocket in every way, except if it fails to connect,
 * or it gets disconnected, it will repeatedly poll until it succesfully connects
 * again.
 *
 * It is API compatible, so when you have:
 *   ws = new WebSocket('ws://....');
 * you can replace with:
 *   ws = new ReconnectingWebSocket('ws://....');
 *
 * The event stream will typically look like:
 *  onconnecting
 *  onopen
 *  onmessage
 *  onmessage
 *  onclose // lost connection
 *  onconnecting
 *  onopen  // sometime later...
 *  onmessage
 *  onmessage
 *  etc... 
 *
 * It is API compatible with the standard WebSocket API.
 *
 * Latest version: https://github.com/joewalnes/reconnecting-websocket/
 * - Joe Walnes
 */
(function (global, factory) {
    if (typeof define === 'function' && define.amd) {
        define([], factory);
    } else if (typeof module !== 'undefined' && module.exports){
        module.exports = factory();
    } else {
        global.ReconnectingWebSocket = factory();
    }
})(this, function () {

    function ReconnectingWebSocket(url, protocols) {
        protocols = protocols || [];

        // These can be altered by calling code.
        this.debug = false;
        this.reconnectInterval = 1000;
        this.reconnectDecay = 0;
        this.reconnectAttempts = 0;
        this.timeoutInterval = 2000;

        var self = this;
        var ws = null;
        var forcedClose = false;
        var timedOut = false;
        var _openEvents = {};// onopen回调事件
        var _closeEvnets = {};// onClose回调事件
        var _connectingEvents = {};// onconnecting回调事件
        var _reTimeout = null;// 定时重连的ID

        this.url = url;
        this.protocols = protocols;
        this.readyState = WebSocket.CONNECTING;
        this.URL = url; // Public API

        this.onopen = function(event) {
        };

        this.onclose = function(event) {
        };

        this.onconnecting = function(event) {
        };

        this.onmessage = function(event) {
        };

        this.onerror = function(event) {
        };

        this.regEvent = function(type, key, callback){
        	switch( type ){
        		case 'onopen':
        			if( _openEvents[key] == undefined ){
        				_openEvents[key] = callback;
        			} else {
        				console.error(key + ' is exist in onopen!');
        			}
        			break;
        		case 'onclose':
        			if( _closeEvnets[key] == undefined ){
        				_closeEvnets[key] = callback;
        			} else {
        				console.error(key + ' is exist in onclose!');
        			}
        			break;
        		case 'onconnecting':
        			if( _connectingEvents[key] == undefined ){
        				_connectingEvents[key] = callback;
        			} else {
        				console.error(key + ' is exist in onconnecting!');
        			}
        			break;
        		default: 
        			console.error("type '"+type+"' is not exist!"); break;
        	}
        };

        this.desEvent = function(type, key){
        	switch( type ){
        		case 'onopen':
        			delete _openEvents[key]; break;
        		case 'onclose':
        			delete _closeEvnets[key]; break;
        		case 'onconnecting':
        			delete _connectingEvents[key]; break;
        		default: 
        			console.error("type '"+type+"' is not exist!"); break;
        	}
        };
        
        function executeCallback(events, data){
        	for(var key in events){
        		events[key].call(this, data);
        	}
        }

        function connect(reconnectAttempt) {
            ws = new WebSocket(url, protocols);
            
            if(!reconnectAttempt)
                self.onconnecting();

            if (self.debug || ReconnectingWebSocket.debugAll) {
                console.log('ReconnectingWebSocket', 'attempt-connect', url);
            }
            
            var localWs = ws;
            var timeout = setTimeout(function() {
                if (self.debug || ReconnectingWebSocket.debugAll) {
                    console.log('ReconnectingWebSocket', 'connection-timeout', url);
                }
                timedOut = true;
                localWs.close();
                timedOut = false;
            }, self.timeoutInterval);

            ws.onopen = function(event) {
                clearTimeout(timeout);
                if (self.debug || ReconnectingWebSocket.debugAll) {
                    console.log('ReconnectingWebSocket', 'onopen', url);
                }
                self.readyState = WebSocket.OPEN;
                executeCallback(_openEvents, self);
                reconnectAttempt = false;
                self.reconnectAttempts = 0;
                self.onopen(event);
            };
            
            ws.onclose = function(event) {
                clearTimeout(timeout);
                ws = null;
                if (forcedClose) {
                    self.readyState = WebSocket.CLOSED;
                    self.onclose(event);
                    executeCallback(_closeEvnets, self);
                } else {
                    self.readyState = WebSocket.CONNECTING;
                    self.onconnecting();
                    executeCallback(_connectingEvents, self);

                    if (!reconnectAttempt && !timedOut) {
                        if (self.debug || ReconnectingWebSocket.debugAll) {
                            console.log('ReconnectingWebSocket', 'onclose', url);
                        }
                        self.onclose(event);
                    }
                    _reTimeout = setTimeout(function() {
                        self.reconnectAttempts++;
                        connect(true);
                    }, self.reconnectInterval + self.reconnectDecay * self.reconnectAttempts);
                }
            };
            ws.onmessage = function(event) {
                if (self.debug || ReconnectingWebSocket.debugAll) {
                    console.log('ReconnectingWebSocket', 'onmessage', url, event.data);
                }
                self.onmessage(event);
            };
            ws.onerror = function(event) {
                if (self.debug || ReconnectingWebSocket.debugAll) {
                    console.log('ReconnectingWebSocket', 'onerror', url, event);
                }
                self.onerror(event);
            };
        }
        connect(false);

        this.send = function(data) {
            if (ws) {
                if (self.debug || ReconnectingWebSocket.debugAll) {
                    console.log('ReconnectingWebSocket', 'send', url, data);
                }
                return ws.send(data);
            } else {
                throw 'INVALID_STATE_ERR : Pausing to reconnect websocket';
            }
        };

        this.close = function() {
            forcedClose = true;
            if (ws) {
                ws.close();
            }
        };

        /**
         * Additional public API method to refresh the connection if still open (close, re-open).
         * For example, if the app suspects bad data / missed heart beats, it can try to refresh.
         */
        this.refresh = function() {
            if (ws) {
                ws.close();
            }
        };
        
        this.reconnect = function(){
        	clearTimeout( _reTimeout );
        	connect( true );
        };
    }

    /**
     * Setting this to true is the equivalent of setting all instances of ReconnectingWebSocket.debug to true.
     */
    ReconnectingWebSocket.debugAll = false;

    return ReconnectingWebSocket;
});
