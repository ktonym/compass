Ext.define('compass.view.locale.TranslationController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.translation',
    controller: 'translation',

    init: function(){
        var lang = localStorage ? (localStorage.getItem('user-lang')||'en'): 'en',
            button = this.getView();
        button.setIconCls(lang);

        if (lang == 'en'){
            button.setText('English');
        } else if (lang == 'ke'){
            button.setText('Kiswahili');
        } else {
            button.setText('Français');
        }

    },

    onMenuItemClick: function(item,e,options){
        var menu = this.getView();

        menu.setIconCls(item.iconCls);
        menu.setText(item.text);

        localStorage.setItem("user-lang", item.iconCls);

        window.location.reload();
    }

});
