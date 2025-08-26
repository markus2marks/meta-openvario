DESCRIPTION = "Linux Kernel for Openvario Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE ?= "^rpi$"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION ?= "6.12.25"
LINUX_RPI_BRANCH ?= "rpi-6.12.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.12"

SRCREV_machine = "3dd2c2c507c271d411fab2e82a2b3b7e0b6d3f16"
SRCREV_meta = "1f6ab68a1d86836bf1b82b791df03da3cfeacb3f"

KMETA = "kernel-meta"
inherit siteinfo rauc-integration

require recipes-kernel/linux/linux-yocto.inc

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=https \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
    ${@bb.utils.contains("INITRAMFS_IMAGE_BUNDLE", "1", "file://initramfs-image-bundle.cfg", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "file://vc4graphics.cfg", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "wm8960", "file://wm8960.cfg", "", d)} \
    file://default-cpu-governor.cfg \
    file://powersave.cfg \
    file://lvds_bridge.cfg \
    file://ov-rpi4-57-lvds-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
    file://ov-rpi5-57-lvds-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
    file://ov-rpi4-7-lvds-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
    file://rauc.cfg \
    "

SRC_URI:append:raspberrypi4 = " \
    file://rpi4-nvmem.cfg \
"

KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG:raspberrypi4 ?= "bcm2711_defconfig"
KBUILD_DEFCONFIG:raspberrypi4-64 ?= "bcm2711_defconfig"
KBUILD_DEFCONFIG:raspberrypi5 ?= "bcm2712_defconfig"
KBUILD_DEFCONFIG:ov-rpi5 ?= "bcm2712_defconfig"

LINUX_VERSION_EXTENSION ?= ""

KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "stmpe-ts", "", d)}"
KERNEL_MODULE_AUTOLOAD:rpi += "i2c-dev i2c-bcm2708"

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

UBOOT_ENTRYPOINT =       "0x00008000"
UBOOT_LOADADDRESS =      "0x00008000"


KERNEL_DTC_FLAGS += "-@ -H epapr"

RDEPENDS:${KERNEL_PACKAGE_NAME}:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-base"
RDEPENDS:${KERNEL_PACKAGE_NAME}-image:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-image"
RDEPENDS:${KERNEL_PACKAGE_NAME}-dev:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dev"
RDEPENDS:${KERNEL_PACKAGE_NAME}-vmlinux:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-vmlinux"
RDEPENDS:${KERNEL_PACKAGE_NAME}-modules:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-modules"
RDEPENDS:${KERNEL_PACKAGE_NAME}-dbg:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dbg"

DEPLOYDEP = ""
DEPLOYDEP:raspberrypi-armv7 = "${RASPBERRYPI_v7_KERNEL}:do_deploy"
do_deploy[depends] += "${DEPLOYDEP}"

SRC_URI:append:rauc-integration = " file://rauc.cfg"
CMDLINE:remove:rauc-integration = "root=/dev/mmcblk0p2"
