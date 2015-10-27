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
                    dataIndex: 'lastUpdate',
                    format: 'Y-m-j H:i:s',
                    filter: true
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
                        handler: function(btn) {
                            me.fireEvent('widgetclick', me, btn);
                        }
                    }
                }
                ]
        );

        /**
         * Custom method to aid in row validation
         *
         */
        me.validateRow = function(record, rowIndex){
            var me = this,
                view = me.getView(),
                errors = record.validate();

            if(errors.isValid){
                return true;
            }

            var columnIndexes = me.getColumnIndexes();

            Ext.each(columnIndexes, function(columnIndex,col){
                    var cellErrors, cell, messages;

                    cellErrors = errors.getByField(columnIndex);

                    if(!Ext.isEmpty(cellErrors)){

                        cell = getCellByPosition({
                           row: rowIndex, column: col
                        });
                        messages = [];
                        Ext.each(cellErrors,function(cellError){
                            messages.push(cellError.message);
                        });
                        cell.addCls('x-form-error-msg x-form-invalid-icon xform-invalid-icon-default');
                        cell.set({
                            'data-errorqtip': Ext.String.format('<ul><li class="last">{0}</li></ul>',
                            messages.join('<br/>'))
                        });
                    }
            });
            return false;
        }

        me.getColumnIndexes = function() {
            var me = this,
                columnIndexes = [];
            Ext.Array.each(me.columns, function (column) {
                if (Ext.isDefined(column.getEditor())) {
                    columnIndexes.push(column.dataIndex);
                } else {
                    columnIndexes.push(undefined);
                }
            });
            return columnIndexes;
        };

        me.validate = function(){
            var me = this,
                isValid = true,
                view = me.getView(),
                error,
                record;
            Ext.each(view.getNodes(), function (row, col) {
                record = view.getRecord(row);
                isValid = (me.validateRow(record, col) && isValid);
            });

            error = isValid ? undefined : {
                title: "Invalid Records",
                message: "Please fix errors before saving."
            };
            return error;

        };

        me.callParent(arguments);
    }
});
