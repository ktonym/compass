Ext.define('compass.view.uw.CorporateController',{
    extend: 'compass.view.base.ViewController',
    alias: 'controller.corporate',
    requires: ['compass.util.Util'],
    createDialog: function(record){
        var me = this,
            view = me.getView(),
            glyphs = compass.util.Glyphs;

        me.dialog=view.add({
            xtype: 'corporate-window',
            viewModel: {
                data: {
                    title: record ? 'Edit ' + record.get('corporateName') : 'Add Corporate',
                    glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
                },
                links: {
                    currentCorporate: record || {
                        type: 'Corporate',
                        create: true
                    }
                }
            }
        });

        me.dialog.show();

        //alert('Create Dialog clicked!');
    }
});
