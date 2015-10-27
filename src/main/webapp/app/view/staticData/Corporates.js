Ext.define('compass.view.staticData.Corporates',{
    extend: 'compass.view.staticData.BaseGrid',
    xtype: 'corporatesgrid',
    store: 'staticData.Corporates',
    columns: [
        {
            width: 20,
            dataIndex: "idCorporate",
            text: "Corp. ID",
            filter: {
                type: 'numeric'
            }
        },
        {
            width: 150,
            dataIndex: "corporateName",
            text: "Organization",
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
            dataIndex: "abbreviation",
            flex: 1,
            text: "Abbreviation",
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "tel",
            flex: 1,
            text: "Telephone"
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
            dataIndex: "postalAddress",
            text: "Postal Address"
        }
    ]

});