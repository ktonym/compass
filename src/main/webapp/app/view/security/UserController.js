
Ext.define("compass.view.security.UserController",{
    extend: "Ext.app.ViewController",
    alias: "controller.user",
    requires: [
        "compass.util.Util"
    ],
    onAdd: function(button,e,options){
        this.createDialog(null)
    },
    onEdit: function(button,e,options){},
    createDialog: function(record){},
    getRecordsSelected: function(){},
    onDelete: function(button,e,options){},
    onSave: function(button,e,options){},
    onSaveSuccess: function(form,action){},
    onSaveFailure: function(form,action){},
    onCancel: function(button,e,options){},
    onRefresh: function(button,e,options){},
    onFileFieldChange: function(fileField,value,options){},
    createDialog: function(record){
        var me = this,
            view = me.getView();
        me.dialog = view.add({
            xtype: 'user-form',
            viewModel: {
                data: {
                    title: record ? 'Edit: ' +
                        record.get('firstName')  : 'Add User'
                },
                links: {
                    currentUser: record || {
                        type: 'User',
                        create: true
                    }
                }
            }
        });
        me.dialog.show();
    }
})
