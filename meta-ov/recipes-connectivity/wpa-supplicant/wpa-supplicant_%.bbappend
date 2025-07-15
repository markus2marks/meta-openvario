FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://wpa_supplicant@.service"
SRC_URI:append = " file://wpa_supplicant-wlan0.conf"

inherit systemd

do_install:append() {
    install -Dm 0644 ${WORKDIR}/wpa_supplicant@.service ${D}${systemd_system_unitdir}/wpa_supplicant@.service
    install -d ${D}/data/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}/data/wpa_supplicant/wpa_supplicant-wlan0.conf
}

SYSTEMD_SERVICE:${PN} += "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"


FILES:${PN} += "/data/wpa_supplicant/wpa_supplicant-wlan0.conf"

