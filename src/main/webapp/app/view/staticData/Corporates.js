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
            editor: {
                allowBlank: false,
                maxLength: 3
            },
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
            dataIndex: "postalAddress",
            text: "Postal Address",
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
            dataIndex: "joined",
            text: "Join Date",
            editor: {
                allowBlank: false,
                xtype: 'datefield'
            }
        }
    ],

    init: function(){
        var me = this;
        me.getStore().reload();
    }

});