SUMMARY = "Enroute Flight Navigation a mobile flight navigation app"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "CLOSED"

inherit systemd

SRC_URI = "git://github.com/Flyberry-System/flyberry-app.git;protocol=https;branch=main \
           file://flyberry-app.service \
           file://xinitrc \
	   file://x11-hdmi.service \
           file://x11-dsi.service \
           file://xinitrc-hdmi \
           file://xinitrc-dsi \
           file://10-dualscreen.conf \
           file://rotation.conf\
           "
SRCREV = "138e5c385e59108c6f8ee137dc70c851dff7e793"

S = "${WORKDIR}/git"

DEPENDS = "qtbase qtdeclarative \
           qtbase-native qtdeclarative-native qttools-native spdlog"

inherit qt6-cmake

#FILES:${PN} += "${WORKDIR}/git/build/default"

do_compile:append() {
    # Prüfen, ob qm-Dateien existieren und dann kopieren
    if ls ${WORKDIR}/git/languages/*.qm 1> /dev/null 2>&1; then
        echo "Kopiere QM-Dateien nach ${WORKDIR}/build/"
        cp -v ${WORKDIR}/git/languages/*.qm ${WORKDIR}/build/
    else
        echo "Keine QM-Dateien im Ordner ${WORKDIR}/git/build/default gefunden"
    fi
}

do_install:append() {
	install -m 0755 -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/flyberry-app.service ${D}${systemd_unitdir}/system/

	# X11: .xinitrc ins Home-Verzeichnis des Users
	install -d 0644 ${D}${ROOT_HOME}
	install -m 0755 ${WORKDIR}/xinitrc ${D}${ROOT_HOME}/.xinitrc

	# Systemd Services
	install -d ${D}${systemd_unitdir}/system
	#install -m 0644 ${WORKDIR}/flyberry-app.service ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/x11-hdmi.service ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/x11-dsi.service ${D}${systemd_unitdir}/system/

	# Xsession-Dateien
	install -d ${D}${ROOT_HOME}
	#install -m 0755 ${WORKDIR}/xinitrc-hdmi ${D}${ROOT_HOME}/.xsession-hdmi
	install -m 0755 ${WORKDIR}/xinitrc-dsi ${D}${ROOT_HOME}/.Xsession

	#install -d ${D}/etc/X11/xorg.conf.d
    	#install -m 0644 ${WORKDIR}/10-dualscreen.conf ${D}/etc/X11/xorg.conf.d/10-dualscreen.conf
    	
    	install -d ${D}/data
    	install -m 0644 ${WORKDIR}/rotation.conf ${D}/data/rotation.conf
}

FILES:${PN} += "/usr/translations/"
FILES:${PN} += "/usr/translations/*"
FILES:${PN} += "/usr/lib"
FILES:${PN} += "/usr/lib/systemd"
FILES:${PN} += "/usr/lib/systemd/system"
FILES:${PN} += "/usr/lib/systemd/system/flyberry-app.service"
FILES:${PN} += "${ROOT_HOME}/.xinitrc"
FILES:${PN} += "${systemd_unitdir}/system"
FILES:${PN} += "${ROOT_HOME}/.xsession-hdmi"
FILES:${PN} += "${ROOT_HOME}/.Xsession"
FILES:${PN} += "/data/rotation.conf"

#FILES:${PN} += "/etc/X11/xorg.conf.d"
#FILES:${PN} += "/etc/X11/xorg.conf.d/10-dualscreen.conf"
               

#SYSTEMD_SERVICE:${PN} += "flyberry-app.service"
#SYSTEMD_AUTO_ENABLE:${PN} = "enable"


#SYSTEMD_SERVICE:${PN} += "x11-hdmi.service x11-dsi.service"
#SYSTEMD_AUTO_ENABLE:${PN} = "enable"
