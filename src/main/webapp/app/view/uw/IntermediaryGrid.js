Ext.define('compass.view.uw.IntermediaryGrid',{
    extend: 'compass.view.base.Grid',
    alias: 'widget.intermediary-grid',
    reference: 'intermediaryGrid',
    plugins:[
        {
            ptype: 'rowexpander',
            rowBodyTpl:[
                '<b>PIN:</b> {pin}</br>',
                '<b>Postal Address:</b> {postalAddress}</br>',
                '<b>Street:</b> {street}, <b>Town:</b> {town}</br>'
            ]
        }
    ],
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
            width: 150,
            dataIndex: "name",
            text: "Name",
            filter: {
                type: 'string'
            }
        },
        {
            width: 100,
            dataIndex: "type",
            text: "Type",
            filter: {
                type: 'string'
            }

        },
        {
            width: 100,
            dataIndex: "tel",
            text: "Telephone",
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "email",
            text: "Email",
            filter: {
                type: 'string'
            }
        },
        {
            width: 150,
            dataIndex: "joinDate",
            format: "d-m-Y",
            text: "Join Date"
        }

    ],

    bind: {
        store: '{intermediaries}'
    }

});
