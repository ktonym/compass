/**
 * Created by akipkoech on 18/09/15.
 */


Ext.define('compass.model.security.Group',{

    extend: 'compass.model.security.Base',

    idProperty: 'idGroup',

    fields: [
        {name: 'idGroup', type: 'int'},
        {name: 'name', type: 'string', mapping: 'groupName' }
    ]

});