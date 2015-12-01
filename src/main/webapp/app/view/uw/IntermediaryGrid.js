Ext.define('compass.view.uw.IntermediaryGrid',{
    extend: 'compass.view.base.Grid',
    alias: 'widget.intermediary-grid',
    reference: 'intermediaryGrid',
    columns: [

        {
            width: 20,
            dataIndex: "idIntermediary",
            text: "Intermediary ID",
            filter: {
                type: 'numeric'
            }
        },
        {
            width: 100,
            dataIndex: "pin",
            text: "PIN",
            filter: {
                type: 'string'
            }
        },
        {
            width: 100,
            dataIndex: "type",
            text: "Type",
            //editor : {
            //    xtype : 'combobox',
            //    store : 'intermediaryTypes',
            //    queryMode : 'local',
            //    displayField : 'text',
            //    valueField : 'text'
            //},
            filter: {
                type: 'string'
            }

        },
        {
            width: 100,
            dataIndex: "tel",
            text: "Telephone",
            //editor: {
            //    allowBlank: false,
            //    maxLength: 45
            //},
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "email",
            text: "Email",
            //editor: {
            //    allowBlank: false,
            //    maxLength: 45
            //},
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "joinDate",
            text: "Join Date"
        }

    ],

    bind: {
        store: '{intermediaries}'
    }

});
