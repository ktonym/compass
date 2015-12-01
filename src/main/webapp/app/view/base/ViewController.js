Ext.define('compass.view.base.ViewController',{
    extend: 'Ext.app.ViewController',
    requires: ['compass.util.Util','compass.util.Glyphs'],
    onAdd: function(button,e,options){
        this.createDialog(null);
    },
    onEdit: function(button){
        this.createDialog(button.getWidgetRecord())
    },
    onDelete: function(button,e,options){
        var record = button.getWidgetRecord();
        Ext.Msg.show({
            title: 'Delete?',
            msg: 'Are you sure you want to delete?',
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(buttonId){
                if (buttonId=='yes'){
                    record.drop();
                }
            }
        });
    },
    onSave: function(button,e,options){

    },
    onSaveSuccess: function(conn, response, options, eOpts) {
        var me=this,
            result = compass.util.Util.decodeJSON(conn.responseText);
        if (result.success) {
            compass.util.Util.showToast('Success! Record saved.');
            me.onCancel();
            me.refresh();
        } else {
            compass.util.Util.showErrorMsg(result.msg);
        }
    },
    onSaveFailure: function(conn, response, options, eOpts){
        compass.util.Util.showErrorMsg(conn.responseText);
    },
    onCancel: function(button,e,options){
        var me=this;
        me.dialog=Ext.destroy(me.dialog);
    }
});