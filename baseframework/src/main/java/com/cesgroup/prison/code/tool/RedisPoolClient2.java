package com.cesgroup.prison.code.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * <p>描述:</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author lihonghui E-mail:li.honghui@cesgroup.com.cn
 * @date 2014-11-29 下午4:27:51 
 * @version V1.0 
 */
public class RedisPoolClient2 {

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池
    static ResourceBundle bundle = null;
    
	static {
		//ResourceBundle bundle = ResourceBundle.getBundle("com/ces/redis/redis");
		bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not found!");
		}
		/*JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.valueOf(bundle
				.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle
				.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"),
				Integer.valueOf(bundle.getString("redis.port")));*/
	}
    
    
    public RedisPoolClient2() 
    { 
        initialPool(); 
        initialShardedPool(); 
        //shardedJedis = shardedJedisPool.getResource(); 
        //jedis = jedisPool.getResource(); 
    } 
 
    /**
     * 初始化非切片池
     */
    private void initialPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        //config.setMaxActive(20); 
        //config.setMaxIdle(5); 
        //config.setMaxWait(1000l); 
        //config.setTestOnBorrow(false); 
        //jedisPool = new JedisPool(config,"127.0.0.1",6379);
        //config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
        config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		//config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
        jedisPool = new JedisPool(config,bundle.getString("redis.hostname").split("#")[0],Integer.valueOf(bundle.getString("redis.port").split("#")[0]));
        
    }
    
    /** 
     * 初始化切片池 
     */ 
    private void initialShardedPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        /*config.setMaxActive(20); 
        config.setMaxIdle(5); 
        config.setMaxWait(1000l); 
        config.setTestOnBorrow(false); */
        
        config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
        
        
        // slave链接 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        //shards.add(new JedisShardInfo("127.0.0.1", 6379, "master")); 
        //shards.add(new JedisShardInfo("127.0.0.1", 6380)); 
        
        
        String[] ips = bundle.getString("redis.hostname").split("#");
        String[] ports = bundle.getString("redis.port").split("#");
        for(int i=0;i<ips.length;i++){
        	if(i==0){
        		shards.add(new JedisShardInfo(ips[i], Integer.valueOf(ports[i]), "master")); 
        	}else
        		shards.add(new JedisShardInfo(ips[i], Integer.valueOf(ports[i]))); 
        }

        // 构造池 
        shardedJedisPool = new ShardedJedisPool(config, shards); 
    } 

    public void show() {  
    	//check();
    	
    	jedis = jedisPool.getResource(); 
    	shardedJedis = shardedJedisPool.getResource(); 
    	
    	intHash();
    	//flushDB();
    	intHashRead();
    	
        //KeyOperate(); 
        //StringOperate(); 
        //ListOperate(); 
        //SetOperate();
        //SortedSetOperate();
        //HashOperate(); 
       
    	//jedisPool.returnResource(jedis);
        //shardedJedisPool.returnResource(shardedJedis);
    	returnResource();
    } 
    
    public void flushDB(){
    	System.out.println(jedis.flushDB()); 
    }
    
    public void returnResource(){
    	jedisPool.returnResource(jedis);
        shardedJedisPool.returnResource(shardedJedis);
    	
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new RedisPoolClient2().show(); 
    }
    
    private void check(){
    	
    	System.out.println("判断key001键是否存在："+shardedJedis.exists("key001")); 
    	System.out.println("判断key001键是否存在："+shardedJedis.get("key001")); 
    }
    
    private void intHash() 
    { 
        System.out.println("======================hash==========================");
        //清空数据 
        //System.out.println(jedis.flushDB()); 
        
        System.out.println("=============增=============");
        System.out.println("hashs中添加key001和value001键值对："+jedis.hset("hashs", "key001", "value001")); 
        System.out.println("hashs中添加key002和value002键值对："+jedis.hset("hashs", "key002", "value002")); 
        System.out.println("hashs中添加key003和value003键值对："+jedis.hset("hashs", "key003", "value003"));
       
        
        System.out.println("=============查=============");
        System.out.println("判断key003是否存在："+shardedJedis.hexists("hashs", "key003"));
        System.out.println("获取key004对应的值："+shardedJedis.hget("hashs", "key004"));
        System.out.println("批量获取key001和key003对应的值："+shardedJedis.hmget("hashs", "key001", "key003")); 
        System.out.println("获取hashs中所有的key："+shardedJedis.hkeys("hashs"));
        System.out.println("获取hashs中所有的value："+shardedJedis.hvals("hashs"));
        System.out.println();
        
        
       System.out.println("hashs中添加key003和value003键值对："+jedis.hset("hashs", "key003", "value003---update"));
        
    }
    
    private void intHashRead() 
    { 
        System.out.println("======================hash==========================");
       
        System.out.println("=============查=============");
        System.out.println("判断key003是否存在："+shardedJedis.hexists("hashs", "key003"));
        System.out.println("获取key004对应的值："+shardedJedis.hget("hashs", "key004"));
        System.out.println("批量获取key001和key003对应的值："+shardedJedis.hmget("hashs", "key001", "key003")); 
        System.out.println("获取hashs中所有的key："+shardedJedis.hkeys("hashs"));
        System.out.println("获取hashs中所有的value："+shardedJedis.hvals("hashs"));
        
        
        //System.out.println("获取hashs中所有的key*："+shardedJedis.hkeys("*"));
        //System.out.println();
        
        
        System.out.println("======================hash-test-start==========================");
        System.out.println("获取hashs中所有的key："+shardedJedis.hkeys("c_4.3.4"));
        System.out.println("获取hashs中所有的value："+shardedJedis.hvals("c_4.3.4"));
        System.out.println("======================hash-test-end==========================");
        
       // System.out.println("hashs中添加key003和value003键值对："+shardedJedis.hset("hashs", "key003", "value003---update"));
        
    }
    
    

    
    private String prefix_code ="c_";//编码前缀
    
    /**
     * 通过dedis 连接master 写入数据
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hsetCode(String key , String field , String value){
    	Long l = null;
    	Jedis jedis = null;
    	try{
    		jedis = jedisPool.getResource(); 
    		l=jedis.hset(prefix_code+key, field, value);
    		System.err.println("保存Redis数据成功，当前组： code.key ="+prefix_code+key+"_"+field+" |  value："+value);
    	}catch(Exception e){
    		 System.err.println("保存Redis数据失败，当前code.key ="+key+" 失败原因："+e.getMessage());
    		//e.printStackTrace();
    	}finally{
    		jedisPool.returnResource(jedis);
    		jedis= null;
    		//returnResource();
    	}
    	return l;
    }
    
    /**
     * 通过共享池获取数据
     * @param key
     * @param field
     * @return
     */
    public String hgetCode(String key , String field){
    	 String result = null;
    	 ShardedJedis shardedJedis=null;
    	 try{
    		 shardedJedis = shardedJedisPool.getResource(); 
    		 result = shardedJedis.hget(prefix_code+key, field);
    		 //System.err.println("获取Redis数据成功，当前code.key ="+key+" result："+result);
    	 }catch(Exception e){
    		 System.err.println("获取Redis数据失败，当前code.key ="+key+" 失败原因："+e.getMessage());
    		 //e.printStackTrace();
    	 }finally{
    		 shardedJedisPool.returnResource(shardedJedis);
    		 shardedJedis=null;
    		// returnResource();
    	 }
    	 return result;
    }
    
/*    
    三、常用命令
    1）连接操作命令
    quit：关闭连接（connection）
    auth：简单密码认证
    help cmd： 查看cmd帮助，例如：help quit
    
    2）持久化
    save：将数据同步保存到磁盘
    bgsave：将数据异步保存到磁盘
    lastsave：返回上次成功将数据保存到磁盘的Unix时戳
    shundown：将数据同步保存到磁盘，然后关闭服务
    
    3）远程服务控制
    info：提供服务器的信息和统计
    monitor：实时转储收到的请求
    slaveof：改变复制策略设置
    config：在运行时配置Redis服务器
    
    4）对value操作的命令
    exists(key)：确认一个key是否存在
    del(key)：删除一个key
    type(key)：返回值的类型
    keys(pattern)：返回满足给定pattern的所有key
    randomkey：随机返回key空间的一个
    keyrename(oldname, newname)：重命名key
    dbsize：返回当前数据库中key的数目
    expire：设定一个key的活动时间（s）
    ttl：获得一个key的活动时间
    select(index)：按索引查询
    move(key, dbindex)：移动当前数据库中的key到dbindex数据库
    flushdb：删除当前选择数据库中的所有key
    flushall：删除所有数据库中的所有key
    
    5）String
    set(key, value)：给数据库中名称为key的string赋予值value
    get(key)：返回数据库中名称为key的string的value
    getset(key, value)：给名称为key的string赋予上一次的value
    mget(key1, key2,…, key N)：返回库中多个string的value
    setnx(key, value)：添加string，名称为key，值为value
    setex(key, time, value)：向库中添加string，设定过期时间time
    mset(key N, value N)：批量设置多个string的值
    msetnx(key N, value N)：如果所有名称为key i的string都不存在
    incr(key)：名称为key的string增1操作
    incrby(key, integer)：名称为key的string增加integer
    decr(key)：名称为key的string减1操作
    decrby(key, integer)：名称为key的string减少integer
    append(key, value)：名称为key的string的值附加value
    substr(key, start, end)：返回名称为key的string的value的子串
    
    6）List 
    rpush(key, value)：在名称为key的list尾添加一个值为value的元素
    lpush(key, value)：在名称为key的list头添加一个值为value的 元素
    llen(key)：返回名称为key的list的长度
    lrange(key, start, end)：返回名称为key的list中start至end之间的元素
    ltrim(key, start, end)：截取名称为key的list
    lindex(key, index)：返回名称为key的list中index位置的元素
    lset(key, index, value)：给名称为key的list中index位置的元素赋值
    lrem(key, count, value)：删除count个key的list中值为value的元素
    lpop(key)：返回并删除名称为key的list中的首元素
    rpop(key)：返回并删除名称为key的list中的尾元素
    blpop(key1, key2,… key N, timeout)：lpop命令的block版本。
    brpop(key1, key2,… key N, timeout)：rpop的block版本。
    rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，

　　　　　　　　　　　　　　并将该元素添加到名称为dstkey的list的头部
    
    7）Set
    sadd(key, member)：向名称为key的set中添加元素member
    srem(key, member) ：删除名称为key的set中的元素member
    spop(key) ：随机返回并删除名称为key的set中一个元素
    smove(srckey, dstkey, member) ：移到集合元素
    scard(key) ：返回名称为key的set的基数
    sismember(key, member) ：member是否是名称为key的set的元素
    sinter(key1, key2,…key N) ：求交集
    sinterstore(dstkey, (keys)) ：求交集并将交集保存到dstkey的集合
    sunion(key1, (keys)) ：求并集
    sunionstore(dstkey, (keys)) ：求并集并将并集保存到dstkey的集合
    sdiff(key1, (keys)) ：求差集
    sdiffstore(dstkey, (keys)) ：求差集并将差集保存到dstkey的集合
    smembers(key) ：返回名称为key的set的所有元素
    srandmember(key) ：随机返回名称为key的set的一个元素
    
    8）Hash
    hset(key, field, value)：向名称为key的hash中添加元素field
    hget(key, field)：返回名称为key的hash中field对应的value
    hmget(key, (fields))：返回名称为key的hash中field i对应的value
    hmset(key, (fields))：向名称为key的hash中添加元素field 
    hincrby(key, field, integer)：将名称为key的hash中field的value增加integer
    hexists(key, field)：名称为key的hash中是否存在键为field的域
    hdel(key, field)：删除名称为key的hash中键为field的域
    hlen(key)：返回名称为key的hash中元素个数
    hkeys(key)：返回名称为key的hash中所有键
    hvals(key)：返回名称为key的hash中所有键对应的value
    hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value*/

    
    
}