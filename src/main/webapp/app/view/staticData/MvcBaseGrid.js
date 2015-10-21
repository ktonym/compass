Ext.define('compass.view.staticData.MvcBaseGrid',{

    extend: 'compass.view.staticData.BaseGrid',

    initComponent: function(){

        var me = this;

        me.columns = Ext.Array.merge(

            me.columns,

            [
                {
                    xtype: 'widgetcolumn',
                    width: 45,
                    sortable: false,
                    menuDisabled: true,
                    itemId: 'delete',
                    widget: {
                        xtype: 'button',
                        glyph: compass.util.Glyphs.getGlyph('destroy'),
                        tooltip: 'Delete',
                        scope: me,
                        handler: function(btn){
                            me.fireEvent('widgetclick', me, btn);
                        }

                    }
                }
            ]

        );

        me.callParent(arguments);

    }
});
