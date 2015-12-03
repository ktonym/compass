Ext.define('compass.view.uw.CorporatesGrid',{
    extend: 'compass.view.base.Grid',
    alias: 'widget.corporates-grid',
    reference: 'corporatesGrid',
    plugins: [{
        ptype: 'rowexpander',
        rowBodyTpl: [
            '<b>Abbreviation:</b> {abbreviation}</br>',
            '<b>Date Joined:</b> {joined}</br>',
            '<b>Postal Address:</b> {postalAddress}</br>'
        ]
    }],
    columns: [
        {
            width: 20,
            dataIndex: "idCorporate",
            text: "Corp. ID"
        },
        {
            width: 150,
            dataIndex: "corporateName",
            text: "Organization"
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
            text: "Email"
        }
    ],
    listeners: {
        itemclick: function(dv, record, item, index, e) {

            alert(record.get('corporateName'));
        }
    },
    bind: {
        store:  '{corporates}'
    }
});