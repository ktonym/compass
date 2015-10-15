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

        me.dockedItems = [
            {
                xtype: 'toolbar',
                dock: 'top',
                itemId: 'topToolbar',
                items: [
                    {
                        itemId: 'add',
                        text: 'Add',
                        glyph: compass.util.Glyphs.getGlyph('add')
                    },
                    {
                        xtype: 'tbseparator'
                    },
                    {
                        itemId: 'save',
                        text: 'Save Changes',
                        glyph: compass.util.Glyphs.getGlyph('saveAll')
                    },
                    {
                        itemId: 'cancel',
                        text: 'Cancel Changes',
                        glyph: compass.util.Glyphs.getGlyph('cancel')
                    },
                    {
                        xtype: 'tbseparator'
                    },
                    {
                        itemId: 'clearFiler',
                        text: 'Clear Filters',
                        glyph: compass.util.Glyphs.getGlyph('clearFilter')
                    }
                ]
            }
        ];

        me.columns = Ext.Array.merge(
            me.columns,
            [
                {
                    xtype : 'datecolumn',
                    text : 'Last Update',
                    width : 150,
                    dataIndex: 'last_update',
                    format: 'Y-m-j H:i:s',
                    filter: true
                },
                {
                    xtype: 'widgetcolumn', //#13
                    width: 45,
                    sortable: false, //#14
                    menuDisabled: true, //#15
                    itemId: 'delete',
                    widget: {
                        xtype: 'button', //#16
                        glyph: Packt.util.Glyphs.getGlyph('destroy'),
                        tooltip: 'Delete',
                        scope: me, //#17
                        handler: function(btn) { //#18
                            me.fireEvent('widgetclick', me, btn);
                        }
                    }
                }
                ]
        );

        me.callParent(arguments);
    }
});
