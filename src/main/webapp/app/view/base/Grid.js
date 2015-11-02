Ext.define('compass.view.base.Grid',{
    extend: 'Ext.grid.Panel',
    requires: [
        'compass.util.Glyphs'
    ],
    columnLines: true,
    viewConfig: {
        stripeRows: true
    },
    initComponent: function() {
        var me = this;
        me.columns = Ext.Array.merge(
            me.columns,
            [{
                xtype : 'datecolumn',
                text : 'Last Update',
                width : 150,
                dataIndex: 'lastUpdate',
                format: 'Y-m-d H:i',
                filter: true
            },{
                xtype: 'widgetcolumn',
                width: 50,
                sortable: false,
                menuDisabled: true,
                widget: {
                    xtype: 'button',
                    glyph: compass.util.Glyphs.getGlyph('edit'),
                    tooltip: 'Edit',
                    handler: 'onEdit'
                }
            }, {
                xtype: 'widgetcolumn',
                width: 45,
                sortable: false,
                menuDisabled: true,
                widget: {
                    xtype: 'button',
                    glyph: compass.util.Glyphs.getGlyph('detail'),
                    tooltip: 'Details',
                    handler: 'OnDetailWidgetClick'

                }
            }]
        );
        me.callParent(arguments);

    }
});