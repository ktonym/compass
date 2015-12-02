Ext.define('compass.view.uw.IntermediaryController',{

    extend: 'compass.view.base.ViewController',
    alias: 'controller.intermediary',
    requires: ['compass.util.Util'],
    createDialog: function(record){
        var me = this,
            view = me.getView(),
            glyphs = compass.util.Glyphs;

        me.dialog = view.add({
            xtype: 'intermediary-window',
            viewModel: {
                data: {
                    title: record ? 'Edit: ' + record.get('name') :
                        'Add Intermediary',
                    glyph: record ? glyphs.getGlyph('edit') :
                        glyphs.getGlyph('add')
                },
                links: {
                    currentIntermediary: record  || {
                        type: 'Intermediary',
                        create: true
                    }
                }
            }
        });
        me.dialog.show();
    },
    onSave: function(button,e,options){
        var me = this,
            dialog = me.dialog,
            form = me.lookupReference('form');
        console.log(Ext.util.JSON.encode(form.getValues()));
        if(form.isValid()){
            Ext.Ajax.request({
                url: 'compass/intermediary/store.json',
                scope: me,
                params: {
                    "data": Ext.util.JSON.encode(form.getValues())
                },
                success: 'onSaveSuccess',
                failure: 'onSaveFailure'
            });
        }
    },
    refresh: function(button,e,options){
        var me = this,
            store = me.getStore('intermediaries');
        store.load();
    }
});
