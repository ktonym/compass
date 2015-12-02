Ext.define('compass.util.Glyphs',{

        singleton: true,

        config: {
            webFont: 'FontAwesome',
            add: 'xf067',
            edit: 'xf040',
            destroy: 'xf1f8',
            save: 'xf00c',
            cancel: 'xf0e2',
            saveAll: 'xf0c7',
            clearFilter: 'xf0b0',
            detail: 'xf06e',
            print: 'xf02f',
            pdf: 'xf1c1',
            excel: 'xf1c3',
            contact: 'xf095',
            corporate: 'xf19c',
            cover: 'xf0e9'
        },

        constructor: function(config){
            this.initConfig(config);
        },

        getGlyph: function(glyph){
            var me=this,
                font=me.getWebFont();
            if(typeof me.config[glyph] === 'undefined'){
                return false;
            }
            return me.config[glyph] + '@' + font;
        }




});
