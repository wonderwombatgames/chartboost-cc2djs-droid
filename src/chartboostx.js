cc.chartboostx = {
	ftable: {},
	connectionId: 0,
	subscribe: function (callerName, location, callback, sender)
	{
		this.connectionId++;
		key = callerName + "_" + location;
		connection = key + "-" + this.connectionId;
		this.ftable[key] =  this.ftable[key] || {};
		this.ftable[key][connection] = {"cb": callback, "obj": sender};
		return connection;
	},
	unsubscribe: function (conn) 
	{
		cc.log(this.ftable);
		keys = conn.split("-");
		delete this.ftable[keys[0]][conn];
	},
    dispatch: function (callerName, location)
    {
    	key = callerName+"_"+location;
        
        if(this.ftable.hasOwnProperty(key))
        {
        	for (var connId in this.ftable[key])
        	{
 				if (this.ftable[key].hasOwnProperty(connId))
 				{
 					cc.log(connId);
 					cc.log(this.ftable[key]);
 					cc.log(this.ftable[key][connId]);
 					callback = this.ftable[key][connId]["cb"];
 					sender = this.ftable[key][connId]["obj"];
 					callback(connId, sender);
  				}
			}
		}
    },
    trigger: function (callerName, location)
    {
	    if (cc.sys.isNative) 
    	{
    		if (cc.sys.OS_ANDROID)
	        {
	            jsb.reflection.callStaticMethod("com/wonderwombat/ChartboostX/ChartboostXBridge", callerName, "(Ljava/lang/String;)V", location);
	        }
	        else if (cc.sys.OS_IOS)
	        {
	            
	        }
	    }
    },
}