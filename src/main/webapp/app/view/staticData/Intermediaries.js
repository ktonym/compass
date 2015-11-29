Ext.define('compass.view.staticData.Intermediaries',{

    extend: 'compass.view.staticData.BaseGrid',
    xtype: 'intermediaries-grid',
    store: 'staticData.Intermediaries',

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
            text: "PIN",
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "tel",
            flex: 1,
            text: "Telephone",
            editor: {
                allowBlank: false,
                maxLength: 45
            },
            filter: {
                type: 'string'
            }
        },
        {
            width: 250,
            dataIndex: "email",
            text: "Email",
            editor: {
                allowBlank: false,
                maxLength: 45
            },
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "joinDate",
            text: "Join Date"
        }

    ]


});
