package pl.mirco;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.text.DecimalFormat;
import java.util.Locale;

//@Theme("mytheme")
@Theme("runo")
@SuppressWarnings("serial")
public class Faktura extends UI {

    public static final String PERSISTENCE_UNIT = "pl.mirco_vfaktura_war_1.0-SNAPSHOTPU";

    public static final String FORMAT_WALUTY = "###,###,##0.00 zł";

    public static final Locale LOCALIZACJA = new Locale("pl", "PL");

    public static final DecimalFormat ZLOTOWKI;

    static {
        ZLOTOWKI = (DecimalFormat) DecimalFormat.getInstance(Faktura.LOCALIZACJA);
        ZLOTOWKI.applyPattern(Faktura.FORMAT_WALUTY);

        //FIXME generowanie danych testowych -> usuń lub zakomentuj
        //DemoDataGenerator.create();
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Faktura.class, widgetset = "pl.mirco.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
    
    private HorizontalSplitPanel horizontalSplit;

    private Component kontrahentView;
    private Component dokumentView;

    @Override
    protected void init(VaadinRequest request) {
        getUI().getSession().setLocale(Faktura.LOCALIZACJA);

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponent(new FakturaToolbar(this));

        horizontalSplit = new HorizontalSplitPanel();
        horizontalSplit.setSplitPosition(10);
        horizontalSplit.setSecondComponent(getDokumentView());
        layout.addComponent(horizontalSplit);
        /* Allocate all available extra space to the horizontal split panel */
        layout.setExpandRatio(horizontalSplit, 1);
        setContent(layout);
    }
    
        private Component getKontrahentView() {
        if (kontrahentView != null) {
            return kontrahentView;
        }

        kontrahentView = new KontrahentView();
        return kontrahentView;
    }

    private Component getDokumentView() {
        if (dokumentView != null) {
            return dokumentView;
        }

        dokumentView = new DokumentView();
        return dokumentView;
    }

    public void pokazDokumenty() {
        horizontalSplit.setSecondComponent(getDokumentView());
    }

    public void pokazKontrahetow() {
        horizontalSplit.setSecondComponent(getKontrahentView());
    }

    // Override the default implementation
//    public static SystemMessages getSystemMessages() {
//        CustomizedSystemMessages messages = new CustomizedSystemMessages();
//        messages.setSessionExpiredCaption("SESJA WYGASŁA!");
//        messages.setSessionExpiredMessage("Musisz ponownie uruchomić aplikację!");
//        messages.setSessionExpiredNotificationEnabled(true);
//        // messages.setSessionExpiredURL("http://vaadin.com/");
//        return messages;
//    }

//    @Override
//    public void terminalError(Terminal.ErrorEvent event) {
//        // Call the default implementation.
//        super.terminalError(event);
//
//        // Some custom behaviour.
//        if (getMainWindow() != null) {
//            getMainWindow().showNotification("Wystąpił błąd!",
//                    "Zgłoś problem do administratora systemu.",
//                    Notification.TYPE_ERROR_MESSAGE);
//        }
//    }

}
