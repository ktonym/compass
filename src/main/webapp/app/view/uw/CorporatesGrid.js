Ext.define('compass.view.uw.CorporatesGrid',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.corporates-grid',
    reference: 'corporatesGrid',
    columns: [
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
    bind: {
        store:  '{corporates}'
    }
});