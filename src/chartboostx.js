cc.chartboostx = {
	ftable: {},
	subscribe: function (callerName, location, callback)
	{
		key = callerName+"_"+location;
		this.ftable[key] =  this.ftable[key] || [] ;
		this.ftable[key].push(callback);
	},
    dispatch: function (callerName, location)
    {
    	key = callerName+"_"+location;
        cc.log( "====== cc.chartboostx -> dispatch( " + key + " )" );
        
        if(this.ftable[key])
        {
	        for (i = 0; i < this.ftable[key].length; i++) { 
	 		  	fnc = this.ftable[key][i];
	 		   	fnc();
			}
		}
        return;
    },
}