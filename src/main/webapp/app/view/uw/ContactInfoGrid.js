Ext.define('compass.view.uw.ContactInfoGrid',{

    extend: 'Ext.grid.Panel',

    xtype: 'contact-info-grid',

    reference: 'contactInfoGrid',

    bind : '{corporatesGrid.selection.contactinfos}',

    columns: [
        {
            width: 20,
            dataIndex: "idContactInfo",
            text: "ID"
        },
        {
            width: 150,
            dataIndex: "fullName",
            text: "Name",
            renderer: function(value,metaData,record){
                metaData['tdAttr'] = 'data-qtip="' +
                    record.get('jobTitle') + '"';
                return value;
            }
        },
//        {
//            width: 150,
//            dataIndex: "jobTitle",
//            text: "Job Title"
//        },
        {
            width: 150,
            dataIndex: "email",
            text: "Email"
        },
        {
            width: 150,
            dataIndex: "tel",
            text: "Tel."
        }
    ]

});