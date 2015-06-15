/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
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
