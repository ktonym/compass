Ext.define('compass.controller.StaticData',{
   extend: 'Ext.app.Controller',
    requires: [
        'compass.util.Util',
        'compass.util.Glyphs'
    ],
    stores: [
        'staticData.Corporates'
    ],
    views: [
        'staticData.Corporates'
    ],
    init: function(application){
        var me = this;
        me.control( {
            'staticdatagrid button#add':{
                click: me.onButtonClickAdd
            },
            'staticdatagrid':{
                edit: me.onEdit
            },
            'staticdatagrid widgetcolumn':{
                itemclick: this.handleWidgetColumn
            },
            'staticdatagrid button#save':{
                click: me.onButtonClickSave
            },
            'staticdatagrid button#cancel':{
                click: this.onButtonClickCancel
            },
            'staticdatagrid button#clearFilter': {
                click: this.onButtonClickClearFilter
            }
        });

        me.listen({
            store: {
                '#staticData.Corporates': {
                    write: this.onStoreSync
                }
            }
        });

    },

    onButtonClickAdd: function(button,e,options){
        var grid = button.up('staticdatagrid'),
            store = grid.getStore(),
            modelName = store.getModel().getName(),
            cellEditing = grid.getPlugin('cellplugin');

        store.insert(0, Ext.create(modelName,{
            lastUpdate: new Date()
        }));

        cellEditing.startEditByPosition({row:0, column:1});
    },

    onEdit: function(editor,context,options){
        context.record.set('lastUpdate',new Date());
    },

    handleWidgetColumn: function(column,action,view,rowIndex,colIndex,item,e){
        var store = view.up('staticdatagrid').getStore(),
            rec = store.getAt(rowIndex);

        if(action=='delete'){
            store.remove(rec);
            Ext.Msg.alert('Delete', 'Save the changes to persist the removed record.');
        }

    },

    onButtonClickSave: function(button,e,options){
        var grid = button.up('staticdatagrid'),
            store = grid.getStore(),
            errors = grid.validate();

        if(errors === undefined){
            store.sync();
        }else{
            Ext.Msg.alert(errors);
        }

    },

    onButtonClickCancel: function(button,e,options){
        button.up('staticdatagrid').getStore().reload();
    },

    onButtonClickClearFilter: function (button, e, options) {
        button.up('staticdatagrid').filters.clearFilters();
    },

    onStoreSync: function(store, operation, options){
        compass.util.Util.showToast('Success! Your changes have been saved.');
    }

});
