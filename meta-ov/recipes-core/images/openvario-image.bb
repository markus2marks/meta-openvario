require openvario-base-image.bb

#IMAGE_ROOTFS_SIZE ?= "3768320"
IMAGE_ROOTFS_SIZE ?= "1048576"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs x11-base"
RDEPENDS_${PN} += "qtbase qtdeclarative qtwayland"

IMAGE_INSTALL += "\
    xcsoar \
    xcsoar-menu \
    xcsoar-profiles \
    xcsoar-maps-default \
    xcsoar-locale \
    caltool \
    sensord \
    variod \
    dtc \
    i2c-tools \
    raspi-gpio \
    net-tools \
    rauc \
    flyberry-app \
    can-utils \
    systemd-networkd \
"

#   xcsoar 
export IMAGE_BASENAME = "openvario-image"


