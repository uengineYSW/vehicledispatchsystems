<template>
    <v-card style="width:450px; height:100%;" outlined>
      <template slot="progress">
        <v-progress-linear color="primary-darker-1" height="10" indeterminate></v-progress-linear>
      </template>
  
      <v-card-title v-if="value._links">
        차량 # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
      </v-card-title>
      <v-card-title v-else>
        차량
      </v-card-title>
  
      <v-card-text style="background-color: white;">
        <String v-if="editMode" label="제조사" v-model="value.manufacturer" :editMode="editMode" :inputUI="''"/>
        <String label="모델" v-model="value.model" :editMode="editMode" :inputUI="''"/>
        <String label="사용 용도" v-model="value.usage" :editMode="editMode" :inputUI="''"/>
        <String label="소유회사" v-model="value.company" :editMode="editMode" :inputUI="''"/>
        <String label="등록번호" v-model="value.registrationNumber" :editMode="editMode" :inputUI="''"/>
        <Boolean label="사용가능여부" v-model="value.inUse" :editMode="editMode"/>
        
        <!-- Additional fields (not sent to backend) -->
        <String label="계약기간" v-model="additionalData.contractPeriod" :editMode="editMode" :inputUI="''"/>
        <Select label="소유구분" v-model="additionalData.ownershipType" :editMode="editMode" :options="ownershipOptions"/>
        <Select label="사용구분" v-model="additionalData.usageType" :editMode="editMode" :options="usageOptions"/>
        <String label="운영부서" v-model="additionalData.operatingDepartment" :editMode="editMode" :inputUI="''"/>
        <Date label="등록일자" v-model="additionalData.registrationDate" :editMode="editMode"/>
        <Number label="주행거리" v-model="additionalData.mileage" :editMode="editMode" suffix="km"/>
        <v-select
            label="이용자"
            v-model="additionalData.user"
            :items="users"
            item-text="name"
            item-value="id"
            :disabled="!editMode"
            >
            <template v-slot:selection="{ item }">
                {{ item.name }} ({{ item.department }})
            </template>
            <template v-slot:item="{ item }">
                {{ item.name }} ({{ item.department }})
            </template>
        </v-select>
        <Date label="이용 시작일" v-model="additionalData.usagePeriodStart" :editMode="editMode"/>
        <Date label="이용 종료일" v-model="additionalData.usagePeriodEnd" :editMode="editMode"/>
        <Date label="엔진오일 최근점검일" v-model="additionalData.lastEngineOilCheckDate" :editMode="editMode"/>
        <Date label="엔진오일 향후점검일" v-model="additionalData.nextEngineOilCheckDate" :editMode="editMode"/>
      </v-card-text>
  
      <v-card-actions style="background-color: white;">
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="edit" v-if="!editMode">
          수정
        </v-btn>
        <div v-else>
          <v-btn color="primary" text @click="save">
            저장
          </v-btn>
          <v-btn color="primary" text @click="remove" v-if="!isNew">
            삭제
          </v-btn>
          <v-btn color="primary" text @click="editMode = false" v-if="!isNew">
            취소
          </v-btn>
        </div>
      </v-card-actions>
      
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn v-if="!editMode" color="primary" text @click="openReportBreakdown">
          고장 신고
        </v-btn>
      </v-card-actions>
  
      <v-snackbar v-model="snackbar.status" :top="true" :timeout="snackbar.timeout" color="error">
        {{ snackbar.text }}
        <v-btn dark text @click="snackbar.status = false">
          Close
        </v-btn>
      </v-snackbar>
    </v-card>
  </template>
  
  <script>
  const axios = require('axios').default;
  
  export default {
    name: 'VehicleManagementCard',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean,
      offline: Boolean,
    },
    data: () => ({
      snackbar: {
        status: false,
        timeout: 5000,
        text: '',
      },
      users: [],
      additionalData: {
        contractPeriod: '',
        ownershipType: '',
        usageType: '',
        operatingDepartment: '',
        registrationDate: '',
        mileage: 0,
        user: null,
        usagePeriodStart: '',
        usagePeriodEnd: '',
        lastEngineOilCheckDate: '',
        nextEngineOilCheckDate: '',
      },
      ownershipOptions: ['소유', '임대', '리스'],
      usageOptions: ['업무용', '영업용', '임원용'],
      userOptions: ['김철수', '이영희', '박민수', '정지영'],
    }),
    created() {
      this.fetchUsers();
    },
    methods: {
      decode(value) {
        return decodeURIComponent(value);
      },
      edit() {
        this.editMode = true;
      },
      async save() {
        try {
          let temp = null;
          if (!this.offline) {
            if (this.isNew) {
              temp = await axios.post(axios.fixUrl('/vehicles'), this.value);
            } else {
              temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value);
            }
          }
  
          if (this.value != null) {
            for (var k in temp.data) this.value[k] = temp.data[k];
          } else {
            this.value = temp.data;
          }
  
          this.editMode = false;
          this.$emit('input', this.value);
  
          if (this.isNew) {
            this.$emit('add', this.value);
          } else {
            this.$emit('edit', this.value);
          }
  
          this.snackbar.status = true;
          this.snackbar.text = '저장되었습니다.';
        } catch (e) {
          this.snackbar.status = true;
          if (e.response && e.response.data.message) {
            this.snackbar.text = e.response.data.message;
          } else {
            this.snackbar.text = e;
          }
        }
      },
      async remove() {
        try {
          if (!this.offline) {
            await axios.delete(axios.fixUrl(this.value._links.self.href));
          }
  
          this.editMode = false;
          this.isDeleted = true;
  
          this.$emit('input', this.value);
          this.$emit('delete', this.value);
  
          this.snackbar.status = true;
          this.snackbar.text = '삭제되었습니다.';
        } catch (e) {
          this.snackbar.status = true;
          if (e.response && e.response.data.message) {
            this.snackbar.text = e.response.data.message;
          } else {
            this.snackbar.text = e;
          }
        }
      },
      async reportBreakdown() {
        try {
            if (!this.offline) {
            await axios.put(axios.fixUrl('/vehicles/reportvehiclebreakdown'), {
                model: this.value.model,
                registrationNumber: this.value.registrationNumber,
                inUse: false,
            });
            this.value.inUse = false;
            this.$emit('input', this.value);
            }

            this.snackbar.status = true;
            this.snackbar.text = '고장이 신고되었습니다.';
        } catch (e) {
            this.snackbar.status = true;
            if (e.response && e.response.data.message) {
            this.snackbar.text = e.response.data.message;
            } else {
            this.snackbar.text = e;
            }
        }
        },
      openReportBreakdown() {
        if (confirm('차량 고장을 신고하시겠습니까?')) {
          this.reportBreakdown();
        }
      },
      async fetchUsers() {
        try {
          const response = await axios.get(axios.fixUrl('/users'));
          this.users = response.data._embedded.users.map(user => ({
            name: user.name,
            id: user.name // Using name as id since we're only fetching names
          }));
        } catch (e) {
          console.error('Error fetching users:', e);
          this.snackbar.status = true;
          this.snackbar.text = 'Failed to fetch users';
        }
      },
    },
  };
  </script>