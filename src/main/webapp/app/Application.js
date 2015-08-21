/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
function loadLocale(){
    var lang = localStorage ? (localStorage.getItem('user-lang')||'en') : 'en',
        file = Ext.util.Format.format("app/view/locale/{0}.js",lang);//,
        //extJsFile = Ext.util.Format.format("ext/packages/ext-locale/build/ext-locale-{0}.js", lang);

   // Ext.Loader.loadScript({url: extJsFile});

    Ext.Loader.loadScript({url: file, onError: function(){
        alert('Error loading locale file. Please contact system administrator.');
    }});
}

loadLocale();

Ext.define('compass.Application', {
    extend: 'Ext.app.Application',
    
    name: 'compass',

    views: ['login.Login'],

    stores: [
        // TODO: add global / shared stores here
    ],
    
    launch: function () {
        Ext.tip.QuickTipManager.init();
        var me = this;
        var task = new Ext.util.DelayedTask(function(){
           me.splashscreen.fadeOut({
               duration: 1000,
               remove: true
           });
           me.splashscreen.next().fadeOut({
               duration: 1000,
               remove: true,
               listeners: {
                   afteranimate: function(el, startTime, eOpts){
                       //Ext.create('compass.view.login.Login').show();
                       Ext.widget('login-dialog');
                   }
               }
           });

        });

        task.delay(2000);

    },

    init: function(){
        var me = this;
        me.splashscreen = Ext.getBody().mask(
            'Loading application', 'splashscreen'
        );
    }




});

Ext.Ajax.on('requestcomplete', function(connection, response){
    try {
        if (response.responseText) {
            var result = Ext.JSON.decode(response.responseText);
            if(result.success === false) {

                switch(result.message) {
                    case '401':
                        Ext.widget('login-dialog');
                        break;
                    case '403':
                        Ext.Msg.show({
                            modal  : true,
                            title  : 'No Permission!',
                            msg    : 'You do not have permission to access this function!',
                            icon   : Ext.Msg.ERROR,
                            buttons: Ext.Msg.OK
                        });
                        break;
                    // handle other messages here
                }
            }
        }
    } catch(err) {
    }
});
