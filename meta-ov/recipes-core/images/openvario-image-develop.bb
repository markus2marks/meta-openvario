require openvario-base-image.bb

#IMAGE_ROOTFS_SIZE ?= "3768320"
IMAGE_ROOTFS_SIZE ?= "1048576"

IMAGE_INSTALL += "\
    enroute \
    fontconfig \
    ttf-opensans \
    dtc \
    i2c-tools \
    raspi-gpio \
    net-tools \
    rauc \
"

#   xcsoar 
export IMAGE_BASENAME = "openvario-image-develop"


