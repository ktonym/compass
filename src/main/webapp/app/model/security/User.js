/**
 * Created by akipkoech on 18/09/15.
 */

Ext.define('compass.model.security.User',{

    extend: 'compass.model.security.Base',

    idProperty: 'username',

    fields: [
        { name: 'username', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'lastName', type: 'string' },
        { name: 'email', type: 'string' },
        { name: 'idGroup', type: 'int' }
    ],

    validators:{
        firstName: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:3, max:20 }
        ],
        lastName: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:3, max:20 }
        ],
        username: [
            { type: 'exclusion', list: ['Admin', 'Operator','Manager'] },
            { type: 'format', matcher: /([a-z]+)/i },
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:3, max:20 }
        ],
        email: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:5, max:20 } ,
            { type: 'email' }
        ],
        idGroup: 'presence'
    }



});