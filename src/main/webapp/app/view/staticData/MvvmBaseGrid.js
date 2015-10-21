Ext.define('compass.view.staticData.MvvmBaseGrid',{

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
                    itemId: 'detail',
                    widget: {
                        xtype: 'button',
                        glyph: compass.util.Glyphs.getGlyph('detail'),
                        tooltip: 'Details',
                        scope: me,
                        handler: 'OnDetailWidgetClick'

                    }
                },
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
                        handler: 'OnDelWidgetClick'

                    }
                }
            ]

        );

        me.callParent(arguments);
    }

});
