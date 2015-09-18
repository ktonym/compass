/**
 * Created by akipkoech on 18/09/15.
 */

Ext.define('compass.model.security.User',{

    extend: 'compass.model.security.Base',

    idProperty: 'username',

    fields: [
        {name: 'username', type: 'string'},
        {name: 'firstName', type: 'string'},
        {name: 'lastName', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'idGroup', type: 'int'}
    ]



});