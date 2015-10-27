Ext.define('compass.view.uw.CorporatesGrid',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.corporates-grid',
    reference: 'corporatesGrid',
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
            dataIndex: "abbreviation",
            flex: 1,
            text: "Abbreviation"
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
        },
        {
            width: 150,
            dataIndex: "postalAddress",
            text: "Postal Address"
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