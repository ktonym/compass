Ext.define('compass.view.staticData.BaseGrid',{

    extend: 'Ext.ux.LiveSearchGridPanel',

    xtype: 'staticdatagrid',

    requires: ['compass.util.Glyphs'],

    columnLines: true,

    viewConfig: {
        stripeRows: true
    },

    initComponent: function(){

        var me=this;

        me.selModel={
            selType: 'cellmodel'
        };

        me.plugins=[
            {
                ptype: 'cellediting',
                clicksToEdit: 2,
                pluginId: 'cellplugin'
            },
            {
                ptype: 'gridfilters'
            }
        ];

        me.callParent(arguments);
    }
});
