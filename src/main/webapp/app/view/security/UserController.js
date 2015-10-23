
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
            console.log(records[0].getData());
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
    onDelete: function(button,e,options){

        var me = this,
            view = me.getView(),
            records = me.getRecordsSelected(), //#1
            store = me.getStore('users'); //#2
        if (store.getCount() >= 2 && records.length){ //#3
            Ext.Msg.show({
                title:'Delete?', //#4
                msg: 'Are you sure you want to delete?',
                buttons: Ext.Msg.YESNO,
                icon: Ext.Msg.QUESTION,
                fn: function (buttonId){
                    if (buttonId == 'yes'){ //#5
                        store.remove(records); //#6
                        store.sync(); //#7
                    }
                }
            });
        } else if (store.getCount() === 1) { //#8
            Ext.Msg.show({
                title:'Warning',
                msg: 'You cannot delete all the users from the application.',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.WARNING
            });
        }

    },
    onSave: function(button,e,options){
        var me = this,
            form = me.lookupReference('form'),
            rec = form.getRecord(),
            vm = me.getViewModel(),
            store = vm.getStore('users');

            if(form && form.isValid){

                Ext.Ajax.request({
                    //clientValidation: true,
                    url: 'compass/user/store.json',
                    scope: me,
                    params : {
                        "data" : Ext.util.JSON.encode(form.getValues())
                    },
                    success: 'onSaveSuccess',
                    failure: 'onSaveFailure'
                });

            }
    },
    onSaveSuccess: function(conn, response, options, eOpts) {
        var me=this,
            result = compass.util.Util.decodeJSON(conn.responseText);
        if (result.success) {
            compass.util.Util.showToast('Success! User saved.');
            me.onCancel();
            me.refresh();
        } else {
            compass.util.Util.showErrorMsg(result.msg);
        }
    },
    onSaveFailure: function(conn, response, options, eOpts){
        compass.util.Util.showErrorMsg(conn.responseText);
    },
//    onSaveSuccess: function(form,action){
//        var me = this;
//        me.onCancel();
//        me.refresh();
//        compass.util.Util.showToast('Success! User saved.');
//    },
//    onSaveFailure: function(form,action){
//        compass.util.Util.handleFormFailure(action);
//    },
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
