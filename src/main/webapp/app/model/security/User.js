Ext.define('compass.model.security.User', {
    extend: 'compass.model.security.Base',
    
    fields: [
        { name: 'firstName', type: 'string' },
        { name: 'lastName', type: 'string' },
        { name: 'userName', type: 'string' },
        { name: 'email', type: 'auto' },
        { name: 'picture', type: 'auto' },
        { name: 'groups_id', type: 'int' }

    ]
});
