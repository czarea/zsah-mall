-- 是否可以抢购，并且扣减库存
-- return -1:库存不足 0:用户重复购买 1:成功

local product_id = KEYS[1]
local user_id = ARGV[1]
local product_key = 'seckill:' .. product_id .. ':stock'

redis.log(redis.LOG_DEBUG,'pid:' .. product_id)

redis.log(redis.LOG_DEBUG,'uid:' .. user_id)

local bought_users_key = 'seckill:' .. product_id .. ':uids'

local is_in = redis.call('sismember',bought_users_key, user_id)

if is_in > 0 then
	return 0
end

local stock = redis.call('get',product_key)

-- 如果库存<=0 ,则返回-1
if not stock or tonumber(stock) <=0 then
	return -1
end

-- 减库存,并且把用户的id添加进已购买用户set里
redis.call('decr',product_key)
redis.call('sadd',bought_users_key,user_id)
return 1




