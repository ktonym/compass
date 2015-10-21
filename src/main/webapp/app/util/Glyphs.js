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
            detail: 'xf06e'
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
