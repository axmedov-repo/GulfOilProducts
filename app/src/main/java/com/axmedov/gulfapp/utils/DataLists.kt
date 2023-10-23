package com.axmedov.gulfapp.utils

import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.entities.AdsData
import com.axmedov.gulfapp.data.entities.ContactData
import com.axmedov.gulfapp.data.entities.NewOilData
import com.axmedov.gulfapp.data.entities.OilData
import com.axmedov.gulfapp.data.entities.ProductData
import com.axmedov.gulfapp.data.entities.SubOilData
import com.axmedov.gulfapp.data.entities.SubProductData
import com.axmedov.gulfapp.data.enums.CountriesEnum
import com.axmedov.gulfapp.data.enums.ProductTypes

val adsDataList = listOf<AdsData>(
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    ),
    AdsData(
        0, R.drawable.vp1
    ),
    AdsData(
        1, R.drawable.vp2
    )
)

val subProductsList = listOf<SubProductData>(
    SubProductData(
        id = 0, name = "Cars"
    ),
    SubProductData(
        id = 1, name = "Motorcycle & Scooter"
    ),
    SubProductData(
        id = 2, name = "Commercial Vehicle"
    ),
    SubProductData(
        id = 3, name = "Industrial"
    )
)

val oilsList = listOf<OilData>(
    OilData(
        id = 0, name = "Cars", image = R.drawable.ic_car
    ),
    OilData(
        id = 1, name = "Motorcycle & Scooter", image = R.drawable.ic_heavy_duty
    ),
    OilData(
        id = 2, name = "Commercial Vehicle", image = R.drawable.ic_commercial_cars
    ),
    OilData(
        id = 3, name = "Industrial", image = R.drawable.ic_industrial_cars
    )
)

val subOilsList = listOf<SubOilData>(
    SubOilData(
        id = 0, name = "Cars", image = R.drawable.ic_car
    ),
    SubOilData(
        id = 1, name = "Motorcycle & Scooter", image = R.drawable.ic_heavy_duty
    ),
    SubOilData(
        id = 2, name = "Commercial Vehicle", image = R.drawable.ic_commercial_cars
    ),
    SubOilData(
        id = 3, name = "Industrial", image = R.drawable.ic_industrial_cars
    )
)

// Products List

val productsListEn = listOf<ProductData>(
    ProductData(
        id = 0,
        name = "Passenger car oil",
        image = R.drawable.ic_car,
        productType = ProductTypes.PASSENGER_CAR
    ),
    ProductData(
        id = 1,
        name = "Truck oil",
        image = R.drawable.ic_commercial_cars,
        productType = ProductTypes.COMMERCIAL
    ),
    ProductData(
        id = 2,
        name = "Transmission oil",
        image = R.drawable.ic_automatic_transmission,
        productType = ProductTypes.AUTOMATIC_TRANSMISSION
    ),
    ProductData(
        id = 3,
        name = "Radiator coolant",
        image = R.drawable.ic_radiator_coolant,
        productType = ProductTypes.RADIATOR_COOLANT
    ),
    ProductData(
        id = 4,
        name = "Brake fluid",
        image = R.drawable.ic_hydraulic_brake,
        productType = ProductTypes.HYDRAULIC_BRAKE_FLUID
    ),
    ProductData(
        id = 5,
        name = "Contacts",
        image = R.drawable.ic_contacts,
        productType = ProductTypes.CONTACT
    )
)

val productsListRu = listOf<ProductData>(
    ProductData(
        id = 0,
        name = "Масло для легковых автомобилей",
        image = R.drawable.ic_car,
        productType = ProductTypes.PASSENGER_CAR
    ),
    ProductData(
        id = 1,
        name = "Масло для грузовых автомобилей",
        image = R.drawable.ic_commercial_cars,
        productType = ProductTypes.COMMERCIAL
    ),
    ProductData(
        id = 2,
        name = "Жидкость для коробки передач",
        image = R.drawable.ic_automatic_transmission,
        productType = ProductTypes.AUTOMATIC_TRANSMISSION
    ),
    ProductData(
        id = 3,
        name = "Антифриз",
        image = R.drawable.ic_radiator_coolant,
        productType = ProductTypes.RADIATOR_COOLANT
    ),
    ProductData(
        id = 4,
        name = "Тормозная жидкость",
        image = R.drawable.ic_hydraulic_brake,
        productType = ProductTypes.HYDRAULIC_BRAKE_FLUID
    ),
    ProductData(
        id = 5,
        name = "Контакты",
        image = R.drawable.ic_contacts,
        productType = ProductTypes.CONTACT
    )
)

// 1.Passenger Car

val passengerCarListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Formula Elite",
        "Premium Catalyst Compatible Passenger Car Motor Oil",
        "gulf_formula_elite_en.pdf"
    ),
    NewOilData(
        1,
        "Gulf Formula G",
        "Passenger Car Motor Oil",
        "gulf_formula_g_en.pdf"
    ),
    NewOilData(
        2,
        "Gulf Max",
        "Passenger Car Motor Oil",
        "gulf_max_en.pdf"
    ),
    NewOilData(
        3,
        "Gulf Max Ultra Plus",
        "Semi-Synthetic Passenger Car Motor Oil",
        "gulf_max_ultra_plus_en.pdf"
    ),
    NewOilData(
        4,
        "Gulf Ultrasynth Gdi",
        "Fully Synthetic Resource Conserving Motor Oil",
        "gulf_ultrasynth_gdi_en.pdf"
    )
)

val passengerCarListRu = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Formula Elite",
        "Premium Catalyst Compatible Passenger Car Motor Oil",
        "gulf_formula_elite_ru.pdf"
    ),
    NewOilData(
        1,
        "Gulf Formula G",
        "Passenger Car Motor Oil",
        "gulf_formula_g_ru.pdf"
    ),
    NewOilData(
        2,
        "Gulf Max",
        "Passenger Car Motor Oil",
        "gulf_max_ru.pdf"
    ),
    NewOilData(
        3,
        "Gulf Max Ultra Plus",
        "Semi-Synthetic Passenger Car Motor Oil",
        "gulf_max_ultra_plus_ru.pdf"
    ),
    NewOilData(
        4,
        "Gulf Ultrasynth Gdi",
        "Fully Synthetic Resource Conserving Motor Oil",
        "gulf_ultrasynth_gdi_ru.pdf"
    )
)

// 2.Commercial

val commercialCarListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Super Duty CF",
        "Commercial Vehicle Engine Oil",
        "gulf_super_duty_cf_en.pdf"
    ),
    NewOilData(
        1,
        "Gulf Super Duty CI-4+",
        "Commercial Vehicle Engine Oil",
        "gulf_super_duty_cl_4+_en.pdf"
    ),
    NewOilData(
        2,
        "Gulf Super Duty LE",
        "Commercial Vehicle Engine Oil",
        "gulf_super_duty_le_en.pdf"
    ),
    NewOilData(
        3,
        "Gulf Superfleet Professional",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_en.pdf"
    ),
    NewOilData(
        4,
        "Gulf Superfleet Supreme",
        "Commercial Vehicle Engine Oil",
        "gulf_superfleet_supreme_en.pdf"
    ),
    NewOilData(
        5,
        "Gulf Super Duty CH-4",
        "Mineral Engine Oil for Heavy Duty Diesel Engines",
        "gulf_super_duty_ch_4_en.pdf"
    ),
    NewOilData(
        6,
        "Gulf Superfleet Professional ECON",
        "Advanced Full Synthetic Engine Oil for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_econ.pdf"
    ),
    NewOilData(
        7,
        "Gulf Superfleet Professional Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_long_drain_en.pdf"
    ),
    NewOilData(
        8,
        "Gulf Superfleet Professional + Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_+long_drain_en.pdf"
    )
)

val commercialCarListRu = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Super Duty CF",
        "Commercial Vehicle Engine Oil",
        "gulf_super_duty_cf_ru.pdf"
    ),
    NewOilData(
        1,
        "Gulf Super Duty CI-4+",
        "Commercial Vehicle Engine Oil",
        "gulf_super_duty_cl_4+_ru.pdf"
    ),
    NewOilData(
        2,
        "Gulf Super Duty LE",
        "Commercial Vehicle Engine Oil",
        "gulf_super_duty_le_ru.pdf"
    ),
    NewOilData(
        3,
        "Gulf Superfleet Professional",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_ru.pdf"
    ),
    NewOilData(
        4,
        "Gulf Superfleet Supreme",
        "Commercial Vehicle Engine Oil",
        "gulf_superfleet_supreme_ru.pdf"
    ),
    NewOilData(
        5,
        "Gulf Super Duty CH-4",
        "Mineral Engine Oil for Heavy Duty Diesel Engines",
        "gulf_super_duty_ch_4_ru.pdf"
    ),
    NewOilData(
        6,
        "Gulf Superfleet Professional ECON",
        "Advanced Full Synthetic Engine Oil for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_econ.pdf"
    ),
    NewOilData(
        7,
        "Gulf Superfleet Professional Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_long_drain_ru.pdf"
    ),
    NewOilData(
        8,
        "Gulf Superfleet Professional + Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_+long_drain_ru.pdf"
    )
)

// 3.Heavy Duty Diesel

val heavyDutyDieselOilsListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Super Duty CH-4",
        "Mineral Engine Oil for Heavy Duty Diesel Engines",
        "gulf_super_duty_ch_4_en.pdf"
    ),
    NewOilData(
        1,
        "Gulf Superfleet Professional ECON",
        "Advanced Full Synthetic Engine Oil for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_econ.pdf"
    ),
    NewOilData(
        2,
        "Gulf Superfleet Professional Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_long_drain_en.pdf"
    ),
    NewOilData(
        3,
        "Gulf Superfleet Professional + Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_+long_drain_en.pdf"
    )
)

val heavyDutyDieselOilsListRu = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Super Duty CH-4",
        "Mineral Engine Oil for Heavy Duty Diesel Engines",
        "gulf_super_duty_ch_4_ru.pdf"
    ),
    NewOilData(
        1,
        "Gulf Superfleet Professional ECON",
        "Advanced Full Synthetic Engine Oil for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_econ.pdf"
    ),
    NewOilData(
        2,
        "Gulf Superfleet Professional Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_long_drain_ru.pdf"
    ),
    NewOilData(
        3,
        "Gulf Superfleet Professional + Long Drain",
        "Synthetic Technology for Heavy Duty Diesel Engines",
        "gulf_superfleet_professional_+long_drain_ru.pdf"
    )
)

// 4.Hydraulic Brake Fluid

val hydraulicBrakeFluidListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Brake Fluid DOT 3",
        "Hydraulic brake fluid",
        "gulf_brake_fluid_dot3_en.pdf"
    ),
    NewOilData(
        1,
        "Gulf Super HD Brake Fluid DOT 4",
        "Extra high performance hydraulic brake fluid",
        "gulf_super_hd_brake_fluid_dot4_en.pdf"
    )
)

val hydraulicBrakeFluidListRu = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Brake Fluid DOT 3",
        "Hydraulic brake fluid",
        "gulf_brake_fluid_dot3_ru.pdf"
    ),
    NewOilData(
        1,
        "Gulf Super HD Brake Fluid DOT 4",
        "Extra high performance hydraulic brake fluid",
        "gulf_super_hd_brake_fluid_dot4_ru.pdf"
    )
)

// 5.Radiator Coolant

val radiatorCoolantListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Eurocool Consentrate",
        "Engine Coolant",
        "gulf_eurocool_consentrate.pdf"
    ),
    NewOilData(
        1,
        "Gulf Eurocool Max",
        "Ultra-High Performance, Premix Radiator Coolant",
        "gulf_eurocool_max_series_red_coolant.pdf"
    ),
    NewOilData(
        2,
        "Gulf Eurocool Series Green",
        "Premium Ready to Use Coolant (Premixed with Water)",
        "gulf_eurocool_series_green_coolant.pdf"
    )
)

// 6.Automatic Transmission

val automaticTransmissionListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf DEXRON® VI ATF",
        "Supreme performance Automatic Transmission Fluid",
        "gulf_dexron_vl_atf_en.pdf"
    ),
    NewOilData(
        1,
        "Gulf Multi-Vehicle ATF",
        "Premium Multi-Vehicle Automatic Transmission Fluid",
        "gulf_multi_vehicle_atf_en.pdf"
    ),
    NewOilData(
        2,
        "Gulf Multi-Vehicle ATF-LV",
        "Premium Synthetic Multi-Vehicle Automatic Transmission Fluid",
        "gulf_multi_vehicle_atf_lv_en.pdf"
    ),
    NewOilData(
        3,
        "Gulf Multi-Vehicle CVT Fluid",
        "Multi-Vehicle Continuously Variable Transmission (CVT) Fluid",
        "gulf_multi_vehicle_cvt_fluid_en.pdf"
    )
)

val automaticTransmissionListRu = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf DEXRON® VI ATF",
        "Supreme performance Automatic Transmission Fluid",
        "gulf_dexron_vl_atf_ru.pdf"
    ),
    NewOilData(
        1,
        "Gulf Multi-Vehicle ATF",
        "Premium Multi-Vehicle Automatic Transmission Fluid",
        "gulf_multi_vehicle_atf_ru.pdf"
    ),
    NewOilData(
        2,
        "Gulf Multi-Vehicle ATF-LV",
        "Premium Synthetic Multi-Vehicle Automatic Transmission Fluid",
        "gulf_multi_vehicle_atf_lv_ru.pdf"
    ),
    NewOilData(
        3,
        "Gulf Multi-Vehicle CVT Fluid",
        "Multi-Vehicle Continuously Variable Transmission (CVT) Fluid",
        "gulf_multi_vehicle_cvt_fluid_ru.pdf"
    )
)

val contactsList = listOf<ContactData>(
    ContactData(
        "CIS Sales Manager",
        "Dilmurod Akhmedov",
        "+998 90 525 41 14",
        CountriesEnum.Uzbekistan
    ),
    ContactData(
        "Gulf oil official distributor in Uzbekistan - \"Bloom\" LLC",
        "Behzod Olimov",
        "+998 99 443 00 11",
        CountriesEnum.Uzbekistan
    ),
    ContactData(
        "Gulf oil official distributor in Azerbaijan - \"Atlas Group\" LTD MMC",
        "Zaka Salmanli",
        "+994 502 124 414",
        CountriesEnum.Azerbaijan
    ),
    ContactData(
        "Gulf oil official distributor in Kyrgyzstan - IP \"Mamazhanov Nomanzhan\"",
        "Islombek Mamazhanov",
        "+996 553 577 776",
        CountriesEnum.Kyrgyzstan
    ),
    ContactData(
        "Gulf oil official distributor in Turkmenistan - \"Future Star\" FZE",
        "Serdar Jumayev",
        "+993 626 751 99",
        CountriesEnum.Turkmenistan
    )
)
