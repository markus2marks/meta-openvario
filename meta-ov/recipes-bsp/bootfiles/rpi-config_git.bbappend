# Appends the config.txt used in the Rapsberry PI boot process to adapt Openvario
# relevant changes

do_deploy:append () {

    CONFIG=${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    # Openvario 57 LVDS
    if [ "${MACHINE}" = "flyberry-rpi4-64" ]; then
        
        # Use the machine specific device tree overlay
        echo "# flyberry" >> $CONFIG
        echo "dtoverlay=flyberry" >> $CONFIG


	echo "# serial interfaces" 		>> $CONFIG
        echo "dtoverlay=uart2" 	>> $CONFIG
        echo "dtoverlay=uart3" 	>> $CONFIG
        echo "dtoverlay=i2c1,pins_44_45" 	>> $CONFIG
        echo "dtoverlay=i2c6,pins_22_23" 	>> $CONFIG
        echo "# display" 			>> $CONFIG
        echo "dtoverlay=vc4-kms-dsi-dxq5d5024" 	>> $CONFIG
        echo "gpio=24=op,dh" 			>> $CONFIG
        echo "# sound driver" >> $CONFIG
 	echo "dtoverlay=max98357a,no-sdmode" 		>> $CONFIG
 	echo "#CAN interface" 		>> $CONFIG
 	echo "dtoverlay=mcp2515-can0,oscillator=8000000,interrupt=17" 		>> $CONFIG
        echo "# Enable SPI bus" >>$CONFIG
        echo "dtparam=spi=on" >>$CONFIG
    fi
    
    if [ "${MACHINE}" = "ov-rpi4-64" ]; then
        
        # Use the machine specific device tree overlay

        echo "# serial interfaces" 		>> $CONFIG
        echo "dtoverlay=uart2" 	>> $CONFIG
        echo "dtoverlay=uart3" 	>> $CONFIG
        echo "# serial interfaces" 		>> $CONFIG
        echo "dtoverlay=i2c1,pins_44_45" 	>> $CONFIG
        echo "# display" 			>> $CONFIG
        echo "dtoverlay=ov-rpi4-57-lvds" 	>> $CONFIG
        echo "gpio=24=op,dh" 			>> $CONFIG
        echo "# sound driver" 			>> $CONFIG
 	echo "dtoverlay=max98357a,no-sdmode" 	>> $CONFIG
 	echo "#CAN interface" 		>> $CONFIG
 	echo "dtoverlay=mcp2515-can0,oscillator=8000000,interrupt=17" 		>> $CONFIG
        echo "# Enable SPI bus" >>$CONFIG
        echo "dtparam=spi=on" >>$CONFIG
    fi
}
