require openvision-image.bb
require ../../recipes-core/package-index/package-index.bb

MACHINE_SPECIFIC_VFD = "${@bb.utils.contains_any("MACHINE", "gbquad4k gbue4k gbx34k gb800se gb800seplus gb800solo gb800ue gb800ueplus gbipbox gbip4k gbquad gbquadplus gbultrase gbultraue gbultraueh gbx1 gbx2 gbx3 gbx3h sezam1000hd xpeedlx mbmini atemio5x00 bwidowx atemio6000 atemio6100 atemio6200 mbminiplus mbhybrid bwidowx2 beyonwizt2 opticumtt evoslim sf128 sf138 bre2zet2c bre2ze4k et1x000 g100 g101 hd51 hd1100 hd1200 hd1265 hd1500 hd500c hd530c formuler3 formuler4 formuler4turbo tiviarmin xcombo enibox mago x1plus sf108 t2cable 9910lx 9911lx 9920lx e4hdcombo odin2hybrid odinplus sh1 h3 h5 h7 lc vs1000 enfinity marvel1 bre2ze xp1000 classm axodin axodinc starsatlx genius evo galaxym6 9900lx sf8008 sf8008m spycat spycatmini spycatminiplus osnino osninoplus gbtrio4k", "", "enigma2-plugin-systemplugins-vfdcontrol", d)}"

BACKUPSUITE_CHECK = "${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash nogamma rpi-vision", "", "enigma2-plugin-extensions-backupsuite", d)}"

BLINDSCAN_CHECK = "${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "enigma2-plugin-systemplugins-dmblindscan", "enigma2-plugin-systemplugins-blindscan", d)}"

TRANSCODING_CHECK = "${@bb.utils.contains_any("MACHINE_FEATURES", "vuplus gigablue dags", "transtreamproxy", "streamproxy", d)}"

ENIGMA2_PLUGINS += "\
	enigma2-plugin-extensions-audiosync \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-extensions-cacheflush", "", d)} \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-openwebif-vision \
	enigma2-plugin-extensions-pictureplayer \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-extensions-pluginskinmover", "", d)} \
	enigma2-plugin-skins-pli-hd \
	${@bb.utils.contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hdmicec \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-osdpositionsetup \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-softwaremanager \
	${@bb.utils.contains_any("MACHINE_FEATURES", "7seg 7segment", "${MACHINE_SPECIFIC_VFD}", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "videoenhancement", "enigma2-plugin-systemplugins-videoenhancement", "", d)} \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-wirelesslan \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-tmbd \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-epgrefresh \
	enigma2-plugin-extensions-reconstructapsc \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-osd3dsetup \
	enigma2-plugin-systemplugins-terrestrialscan", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "", " \
	enigma2-plugin-skins-octetfhd \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-systemplugins-autobouquetsmaker", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash libeplayer", "", "enigma2-plugin-systemplugins-serviceapp", d)} \
	"

DEPENDS += "\
	enigma2 \
	enigma2-locale-meta \
	enigma2-plugins \
	"

RDEPENDS += "\
	${@bb.utils.contains("MACHINE_FEATURES", "emmc", "dosfstools mtools e2fsprogs-resize2fs partitions-by-name" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "fastboot", "dosfstools mtools android-tools" , "", d)} \
	"

# These machine feature related plugins should not be enabled for smallflash STBs as there isn't enough space for them!
MACHINE_FEATURE_RELATED_PLUGINS += "\
	${@bb.utils.contains("MACHINE_FEATURES", "bluetooth", "enigma2-plugin-extensions-btdevicesmanager", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvd", "enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "grautec", "enigma2-plugin-extensions-grautec", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "nogamma", "enigma2-plugin-extensions-rcuselect", "", d)} \
	${@bb.utils.contains_any("MACHINE", "enfinity marvel1 bre2ze", "enigma2-plugin-systemplugins-ewvfdcontrol", "", d)} \
	${@bb.utils.contains_any("MACHINE", "sf128 sf138 bre2zet2c bre2ze4k et1x000 g100 g101 hd51 hd1100 hd1200 hd1265 hd1500 hd500c hd530c formuler3 formuler4 formuler4turbo tiviarmin xcombo enibox mago x1plus sf108 t2cable 9910lx 9911lx 9920lx e4hdcombo odin2hybrid odinplus sh1 h3 h5 h7 lc vs1000", "enigma2-plugin-systemplugins-f3ledcontrol", "", d)} \
	${@bb.utils.contains_any("MACHINE", "gbquad4k gbue4k gbx34k gb800se gb800seplus gb800solo gb800ue gb800ueplus gbipbox gbip4k gbquad gbquadplus gbultrase gbultraue gbultraueh gbx1 gbx2 gbx3 gbx3h gbtrio4k", "enigma2-plugin-systemplugins-gigabluevfdcontrol", "", d)} \
	${@bb.utils.contains_any("MACHINE", "sezam1000hd xpeedlx mbmini atemio5x00 bwidowx atemio6000 atemio6100 atemio6200 mbminiplus mbhybrid bwidowx2 beyonwizt2 opticumtt evoslim", "enigma2-plugin-systemplugins-inivfdcontrol", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "multitranscoding", "enigma2-plugin-systemplugins-multitranscodingsetup", "", d)} \
	${@bb.utils.contains_any("MACHINE", "classm axodin axodinc starsatlx genius evo galaxym6 9900lx", "enigma2-plugin-systemplugins-odinm7vfdcontrol", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "satip", "enigma2-plugin-systemplugins-satipclient" , "", d)} \
	${@bb.utils.contains("MACHINE", "xp1000", "enigma2-plugin-systemplugins-sf8vfdcontrol", "", d)} \
	${@bb.utils.contains_any("MACHINE", "sf8008 sf8008m spycat spycatmini spycatminiplus osnino osninoplus", "enigma2-plugin-systemplugins-vpledcontrol", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvd", "cdtextinfo", "", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "streamproxy transcoding multitranscoding", "${TRANSCODING_CHECK}", "", d)} \
	"

IMAGE_INSTALL += "\
	aio-grab \
	cronie \
	enigma2 \
	enigma2-locale-meta \
	${ENIGMA2_PLUGINS} \
	frequency-xml-list-atsc \
	frequency-xml-list-cables \
	frequency-xml-list-satellites \
	frequency-xml-list-terrestrial \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-block2mtd libcrypto", "", d)} \
	libavahi-client \
	settings-autorestore \
	tuxbox-links \
	${@bb.utils.contains_any("MACHINE", "vuuno4kse vuultimo4k vuduo4k", "vuplus-hdmi-in-helper", "", d)} \
	wget \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	${MACHINE_FEATURE_RELATED_PLUGINS} \
	ntp", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "", " \
	curl \
	nfs-utils \
	openssh-sftp-server \
	"

export IMAGE_BASENAME = "openvision-enigma2"
