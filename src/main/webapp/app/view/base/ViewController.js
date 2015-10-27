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
    onCancel: function(button,e,options){
        var me=this;
        me.dialog=Ext.destroy(me.dialog);
    }
});