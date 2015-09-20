
Ext.define("compass.view.security.UserController",{
    extend: "Ext.app.ViewController",
    alias: "controller.user",
    requires: [
        "compass.util.Util"
    ],
    onAdd: function(button,e,options){
        this.createDialog(null)
    },
    onEdit: function(button,e,options){
        var me = this,
            records = me.getRecordsSelected();

        if (records[0]){
            me.createDialog(records[0]);
        }

    },
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
    },
    getRecordsSelected: function(){
        var grid = this.lookupReference('usersGrid');
        return grid.getSelection();
    },
    onDelete: function(button,e,options){},
    onSave: function(button,e,options){
        var me = this,
            form = me.lookupReference('form');

        if(form && form.isValid){
            form.submit({
                clientValidation: true,
                url: '/compass/user/store',
                scope: me,
                success: 'onSaveSuccess',
                failure: 'onSaveFailure'
            });
        }

    },
    onSaveSuccess: function(form,action){
        var me = this;
        me.onCancel();
        me.refresh();
        compass.util.Util.showToast('Success! User saved.');
    },
    onSaveFailure: function(form,action){
        compass.util.Util.handleFormFailure(action);
    },
    onCancel: function(button,e,options){
        var me = this;
        me.dialog = Ext.destroy(me.dialog);
    },
    refresh: function(button,e,options){
        var me = this,
            store = me.getStore('users');
        store.load();
    },
    onFileFieldChange: function(fileField,value,options){}
})
