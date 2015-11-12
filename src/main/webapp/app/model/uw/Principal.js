Ext.define('compass.model.uw.Principal',{
    extend: 'compass.model.uw.Base',
    entityName: 'Principal',
    idProperty: 'idPrincipal',
    fields: [
        { name: 'idPrincipal', type: 'int', useNull: true },
        { name: 'familyNo', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'otherNames', type: 'string' },
        { name: 'dob', type: 'date', dateFormat: 'Ymd' },
        { name: 'idCorporate', reference: 'Corporate'}
    ],
    //hasOne: [
    //    {
    //        model: 'Corporate',
    //        name: 'corporate',
    //        foreignKey: 'idCorporate',
    //        associationKey: 'corporate'
    //    }
    //],
    validators: {
        familyNo: [
            { type: 'format', matcher: /^\S{3}-\d{2,5}$/, message: 'Family number must be in the format AAA-99999'}
        ],
        firstName: [{ type: 'presence', message: 'This field is mandatory' }],
        surname: [{ type: 'presence', message: 'This field is mandatory' }],
        idCorporate: 'presence'
    }
});