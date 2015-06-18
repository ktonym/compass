Ext.define('compass.view.login.LoginController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',
    requires: [
        'compass.view.login.CapsLockTooltip',
        'compass.util.Util',
        'compass.util.SessionMonitor'
    ],

    onTextFieldSpecialKey: function(field,e,options){
        if (e.getKey() === e.ENTER){
            this.doLogin();
        }
    },
    onTextFieldKeyPress: function(field,e,options){

        var charCode = e.getCharCode(),
            me = this;

        if((e.shiftKey && charCode >= 97 && charCode <= 122) ||
            (!e.shiftKey && charCode >= 65 && charCode <= 90)){

            if(me.capsLockTooltip === undefined){
                me.capsLockTooltip = Ext.widget('capslocktooltip');
            }
            me.capsLockTooltip.show();
        } else {
            if(me.capsLockTooltip !== undefined){
                me.capsLockTooltip.hide();
            }
        }

    },
    onButtonClickCancel: function(button,e,options){
        this.lookupReference('form').reset();
    },
    onButtonClickSubmit: function(button,e,options){
        var me=this;
        if(me.lookupReference('form').isValid()){
            me.doLogin();
        }
    },
    doLogin: function(){
        var me = this,
            form = me.lookupReference('form');
        this.getView().mask('Authenticating... Please wait...');
        form.submit({
            clientValidation: true,
            url: 'compass/security/logon',
            scope: me,
            success: 'onLoginSuccess',
            failure: 'onLoginFailure'
        });

    },
    onLoginFailure: function(form,action){
        this.getView().unmask();
        var result = Ext.JSON.decode(action.response.responseText,true);
        if (!result){
            result = {};
            result.success = false;
            result.msg = action.response.responseText;
        }

        switch (action.failureType){
            case Ext.form.action.Action.CLIENT_INVALID:
                compass.util.Util.showErrorMsg( 'Form fields may not be submitted with invalid values');
            break;
            case Ext.form.action.Action.CONNECT_FAILURE:
                compass.util.Util.showErrorMsg(action.response.responseText);
            break;
            case Ext.form.action.Action.SERVER_INVALID:
                compass.util.Util.showErrorMsg(result.msg);

        }

    },
    onLoginSuccess: function(form,action){
        this.getView().unmask();
        this.getView().close();
        Ext.create('compass.view.main.Main').show();
        compass.util.SessionMonitor.start();
    }
});
