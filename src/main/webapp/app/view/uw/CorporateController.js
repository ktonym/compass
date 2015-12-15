Ext.define('compass.view.uw.CorporateController',{
    extend: 'compass.view.base.ViewController',
    alias: 'controller.corporate',
    requires: ['compass.util.Util'],
    createDialog: function(record){
        var me = this,
            view = me.getView(),
            glyphs = compass.util.Glyphs;
        me.isEdit = !!record;
        me.dialog=view.add({
            xtype: 'corporate-window',
            viewModel: {
                data: {
                    title: record ? 'Edit: ' + record.get('corporateName') : 'Add Corporate',
                    glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
                },
                links: {
                    currentCorporate: record || {
                        type: 'Corporate',
                        create: true
                    }
                }
            },
            session : true
        });

        me.dialog.show();

        //alert('Create Dialog clicked!');
    },
    onSave: function(button,e,options){
        var me = this,
            form = me.lookupReference("form"),
            dialog = me.dialog,
            isEdit = me.isEdit,
            session = me.getSession(),
            id;
        if(form.isValid()){
            if(!isEdit){
                id = dialog.getViewModel().get('currentCorporate').id;
            }
            dialog.getSession().save();
            if(!isEdit){
                me.getStore('corporates').add(session.getRecord('Corporate',id));
            }
            me.onCancel();
        }
        var batch = session.getSaveBatch();
        if (batch){
            batch.start();
        }
    },

    hideTabs: function(){
        var me = this;
        if(me.isEdit){

        }

    },

    onSelectCover: function(combo,record,eOpts){
        Ext.Msg.alert('Selected ' + record);
    },

    onAddCover: function(button,e,options){
        Ext.Msg.alert('adding new cover');
    },

    onAnnivsCxtMnu: function(grid, record, item, index, e, eOpts){
        Ext.Msg.alert('Right Clicked!');
    }
});
