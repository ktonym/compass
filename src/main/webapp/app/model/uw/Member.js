Ext.define('compass.model.uw.Member',{

    extend: 'compass.model.uw.Base',
    entityName: 'Member',
    idProperty: 'idMember',

    fields: [
        { name: 'idMember', type: 'int', useNull: true },
        { name: 'memberNo', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'otherNames', type: 'string' },
        { name: 'sex', type: 'gender' },
        { name: 'dob', type: 'date', dateFormat: 'Ymd' },
        { name: 'memberType', type: 'membertype' }
    ],

    manyToMany: 'CorpAnniv',

    hasOne: [
        {
            model: 'Principal',
            name: 'principal',
            foreignKey: 'idPrincipal',
            associationKey: 'principal'
        }
    ],

    validators: {
        memberNo: [
            { type: 'format', matcher: /^\S{3}-\d{5}-\d{2}$/, message: 'Member number must be in the format AAA-99999-99' }
        ],
        firstName: [{ type: 'presence', message: 'This field is mandatory' }],
        surname: [{ type: 'presence', message: 'This field is mandatory' }],
        sex: [{ type: 'presence', message: 'This field is mandatory' }],
        dob: [{ type: 'presence', message: 'This field is mandatory' }],
        memberType: [{ type: 'presence', message: 'This field is mandatory' }]

    }


});
