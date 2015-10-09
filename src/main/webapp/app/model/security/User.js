Ext.define('compass.model.security.User',{

    extend: 'compass.model.security.Base',

    idProperty: 'username',

    fields: [
        { name: 'username', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'lastName', type: 'string' },
        { name: 'email', type: 'string' },
        { name: 'idGroup', type: 'int' },
        { name:'groupName', type:'string', persist:false,
            convert:function(v, rec){
                var data = rec.data;
                if (data.group && data.group.name){
                    return data.group.name;
                }
                return data.idGroup;
            }
        }

    ],
    hasOne: [
        {
            model: 'Group',
            name: 'group',
            foreignKey: 'idGroup',
            associationKey: 'group'
        }
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
            { type: 'length', min:5, max:40 } ,
            { type: 'email' }
        ],
        idGroup: 'presence'
    }



});