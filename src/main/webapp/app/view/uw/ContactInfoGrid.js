Ext.define('compass.view.uw.ContactInfoGrid',{

    extend: 'Ext.grid.Panel',
    xtype: 'contact-info-grid',
    reference: 'contactInfoGrid',
    requires: ['compass.util.Glyphs'],
    bind : '{corporatesGrid.selection.contacts}',
    border: true,
    title: 'Contact Info',
    glyph: compass.util.Glyphs.getGlyph('contact'),
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
                    record.get('jobTitle') + ' ' + record.get('fullName') + '"';
                return value;
            }
        },
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