require openvario-base-image.bb

#IMAGE_ROOTFS_SIZE ?= "3768320"
IMAGE_ROOTFS_SIZE ?= "1048576"

IMAGE_INSTALL += "\
    xcsoar \
    xcsoar-menu \
    xcsoar-profiles \
    xcsoar-maps-default \
    caltool \
    sensord \
    variod \
    dtc \
    i2c-tools \
    net-tools \
    rauc \
    can-utils \
    bcm2835-tests \
    raspi-gpio \
    libgpiod \
    libgpiod-tools \
    flyberry-app \
"

#   xcsoar 
export IMAGE_BASENAME = "openvario-image"


