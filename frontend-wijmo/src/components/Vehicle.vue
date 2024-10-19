<template>
    <div>
        <String label="제조사" v-model="value.manufacturer" :editMode="editMode"/>
        <String label="모델" v-model="value.model" :editMode="editMode"/>
        <String label="사용 용도" v-model="value.usage" :editMode="editMode"/>
        <String label="소유회사" v-model="value.company" :editMode="editMode"/>
        <String label="등록번호" v-model="value.registrationNumber" :editMode="editMode"/>
        <Boolean label="사용가능여부" v-model="value.inUse" :editMode="editMode"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'Vehicle',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "vehicles"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

