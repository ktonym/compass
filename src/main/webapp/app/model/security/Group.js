
Ext.define('compass.model.security.Group',{

    extend: 'compass.model.security.Base',

    idProperty: 'idGroup',

    entityName: 'Group',

    fields: [
        {name: 'idGroup', type: 'int', useNull: true},
        {name: 'groupName', type: 'string'}//, mapping: 'groupName' }
    ]

});